package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserItemInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.EventOutputPort;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.ReturnItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.event.ItemReturned;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ReturnItemInputPort implements ReturnItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    public ReturnItemInputPort(RentalCardOutputPort rentalCardOutputPort, EventOutputPort eventOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
        this.eventOutputPort = eventOutputPort;
    }


    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        // OutputPort 를 사용해서 rentalCard 검색 후에 없으면, Exception 처리, 있으면 도서 반납
        RentalCard findRentalCard = rentalCardOutputPort.loadRentalCard(returnDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item returnItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        // 대여한 도서를 반납한다.
        findRentalCard.returnItem(returnItem, LocalDate.now());
        rentalCardOutputPort.save(findRentalCard);
        // return event 발행
        ItemReturned itemReturnEvent = RentalCard.createItemReturnEvent(findRentalCard.getMember(), returnItem, 10);
        // 이벤트 발행
        eventOutputPort.occurReturnEvent(itemReturnEvent);
        return RentalCardOutputDTO.mapToDTO(findRentalCard);
    }
}
