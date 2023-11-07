package kr.co.won.rentalcardservice.appilcation.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.won.rentalcardservice.domain.model.event.ItemRented;
import kr.co.won.rentalcardservice.domain.model.event.ItemReturned;
import kr.co.won.rentalcardservice.domain.model.event.OverDueCleared;
import org.springframework.stereotype.Component;

@Component
public interface EventOutputPort {

    default void occurRentalEvent(ItemRented rentedEvent) throws JsonProcessingException {

    }

    default void occurReturnEvent(ItemReturned itemReturnedEvent) throws JsonProcessingException {

    }

    default void occurOverdueClearEvent(OverDueCleared overDueCleared) throws JsonProcessingException {

    }
}
