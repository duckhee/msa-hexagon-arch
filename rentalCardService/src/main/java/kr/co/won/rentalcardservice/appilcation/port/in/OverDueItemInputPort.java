package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserItemInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.OverDueItemUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OverDueItemInputPort implements OverDueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    public OverDueItemInputPort(RentalCardOutputPort rentalCardOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
    }

    @Override
    public RentalCardOutputDTO overDueItem(UserItemInputDTO userItemInputDTO) {
        RentalCard findRentalCard = rentalCardOutputPort.loadRentalCard(userItemInputDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item overDueItem = new Item(userItemInputDTO.getItemId(), userItemInputDTO.getItemTitle());

        findRentalCard.overdueItem(overDueItem);
        // dirty checking 으로 대체
//        rentalCardOutputPort.save(findRentalCard);
        return RentalCardOutputDTO.mapToDTO(findRentalCard);
    }
}
