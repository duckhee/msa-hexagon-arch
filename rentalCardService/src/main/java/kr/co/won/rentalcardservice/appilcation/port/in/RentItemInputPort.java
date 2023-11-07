package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserItemInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.EventOutputPort;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.RentItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.event.ItemRented;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // -> 해당 transactional의 경우, 데이터 베이스와 이벤트의 정합성을 지켜주기 위해서 @Transaction이 필요하다.
public class RentItemInputPort implements RentItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;


    public RentItemInputPort(RentalCardOutputPort rentalCardOutputPort, EventOutputPort eventOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
        this.eventOutputPort = eventOutputPort;
    }

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception {
        RentalCard newOrFindRentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
                .orElseGet(() -> RentalCard.createRentalCard(new IDName(rental.getUserId(), rental.getUserName())));

        Item newItem = new Item(rental.getItemId(), rental.getItemTitle());
        newOrFindRentalCard.rentItem(newItem);
        // 명시적으로 저장 하는 것 -> Dirty Checking 을 이용해서 처리도 가능하다.
        RentalCard updateRentalCard = rentalCardOutputPort.save(newOrFindRentalCard);
        // 대여 이벤트 생성
        ItemRented itemRentalEvent = RentalCard.createItemRentalEvent(newOrFindRentalCard.getMember(), newItem, 10);
        // 이벤트 발행
        eventOutputPort.occurRentalEvent(itemRentalEvent);
        return RentalCardOutputDTO.mapToDTO(updateRentalCard);
    }
}
