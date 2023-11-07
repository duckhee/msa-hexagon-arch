package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.ClearOverdueInfoDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.RentalResultOutputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.EventOutputPort;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.ClearOverdueItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.event.OverDueCleared;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClearOverDueItemInputPort implements ClearOverdueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    public ClearOverDueItemInputPort(RentalCardOutputPort rentalCardOutputPort, EventOutputPort eventOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
        this.eventOutputPort = eventOutputPort;
    }

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalCard findRentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
        // 연체료를 삭감을 진행하는 로직 실행
        findRentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());
        // 명시적으로 처리
        rentalCardOutputPort.save(findRentalCard);
        // 이벤트 생성
        OverDueCleared overdueClearEvent = RentalCard.createOverdueClearEvent(findRentalCard.getMember(), clearOverdueInfoDTO.getPoint());
        // 이벤트 발행
        eventOutputPort.occurOverdueClearEvent(overdueClearEvent);
        return RentalResultOutputDTO.matToDTO(findRentalCard);
    }
}
