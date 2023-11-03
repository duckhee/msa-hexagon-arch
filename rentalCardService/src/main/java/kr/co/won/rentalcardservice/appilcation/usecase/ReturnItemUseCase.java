package kr.co.won.rentalcardservice.appilcation.usecase;

import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserItemInputDTO;

public interface ReturnItemUseCase {

    RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception;
}
