package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.ClearOverdueInfoDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.RentalResultOutputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.ClearOverdueItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClearOverDueItemInputPort implements ClearOverdueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    public ClearOverDueItemInputPort(RentalCardOutputPort rentalCardOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
    }

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalCard findRentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
        // 연체료를 삭감을 진행하는 로직 실행
        findRentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());
        // 명시적으로 처리
        rentalCardOutputPort.save(findRentalCard);
        return RentalResultOutputDTO.matToDTO(findRentalCard);
    }
}
