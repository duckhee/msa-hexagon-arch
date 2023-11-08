package kr.co.won.rentalcardservice.appilcation.usecase;

import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;

public interface CompensationUseCase {

    default RentalCard cancelRentItem(IDName idName, Item item) {
        return null;
    }

    default RentalCard cancelReturnItem(IDName idName, Item item, long point) throws Exception {
        return null;
    }

    default long cancelMakeAvailableRental(IDName idName, long point) {
        return 0;
    }
}
