package kr.co.won.rentalcardservice.appilcation.usecase;

import kr.co.won.rentalcardservice.adapter.web.dto.RentItemOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.ReturnItemOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserInputDTO;

import java.util.List;
import java.util.Optional;

public interface InQueryUseCase {

    default Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return Optional.empty();
    }

    default Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO) {
        return Optional.empty();
    }

    default Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO userInputDTO) {
        return Optional.empty();
    }
}
