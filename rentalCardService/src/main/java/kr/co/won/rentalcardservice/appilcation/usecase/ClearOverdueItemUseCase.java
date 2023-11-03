package kr.co.won.rentalcardservice.appilcation.usecase;

import kr.co.won.rentalcardservice.adapter.web.dto.ClearOverdueInfoDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.RentalResultOutputDTO;

public interface ClearOverdueItemUseCase {

    RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception;
}
