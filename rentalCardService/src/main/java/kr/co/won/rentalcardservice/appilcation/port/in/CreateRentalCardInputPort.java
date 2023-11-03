package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.CreateRentalCardUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Rental Card 를 생성하는 기능을 구현 하는 것
 */
@Service
@Transactional
public class CreateRentalCardInputPort implements CreateRentalCardUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    public CreateRentalCardInputPort(RentalCardOutputPort rentalCardOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
    }


    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO) {
        // 객체를 생성하는 것은 도메인에서 처리
        RentalCard newRentalCard = RentalCard.createRentalCard(new IDName(userInputDTO.getUserId(), userInputDTO.getUserName()));
        // 단순하게 데이터 저장만 한다. -> 흐름 제어만 하고 있다고 할 수 있다.
        RentalCard savedRentalCard = rentalCardOutputPort.save(newRentalCard);

        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
