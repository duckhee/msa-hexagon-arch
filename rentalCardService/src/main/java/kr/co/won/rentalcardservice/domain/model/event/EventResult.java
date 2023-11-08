package kr.co.won.rentalcardservice.domain.model.event;

import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResult implements Serializable {
    private EventType eventType;
    private boolean isSuccess;
    private IDName idName;
    private Item item;
    private long point;
}
