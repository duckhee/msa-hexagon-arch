package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserItemInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.RentItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentItemInputPort implements RentItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;


    public RentItemInputPort(RentalCardOutputPort rentalCardOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
    }

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception {
        RentalCard newOrFindRentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
                .orElseGet(() -> RentalCard.createRentalCard(new IDName(rental.getUserId(), rental.getUserName())));

        Item newItem = new Item(rental.getItemId(), rental.getItemTitle());
        newOrFindRentalCard.rentItem(newItem);
        // 명시적으로 저장 하는 것 -> Dirty Checking 을 이용해서 처리도 가능하다.
        RentalCard updateRentalCard = rentalCardOutputPort.save(newOrFindRentalCard);
        return RentalCardOutputDTO.mapToDTO(updateRentalCard);
    }
}
