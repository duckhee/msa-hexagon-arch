package kr.co.won.rentalcardservice.appilcation.usecase;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserInputDTO;

public interface CreateRentalCardUseCase {

    RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO);
}
