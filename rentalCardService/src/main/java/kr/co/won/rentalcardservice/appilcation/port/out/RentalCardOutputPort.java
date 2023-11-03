package kr.co.won.rentalcardservice.appilcation.port.out;

import kr.co.won.rentalcardservice.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adapter 에서 해당 기능을 구현을 해준다.
 */
@Repository
public interface RentalCardOutputPort {

    default Optional<RentalCard> loadRentalCard(String userId) {
        return Optional.empty();
    }

    default RentalCard save(RentalCard rentalCard) {
        return null;
    }
}
