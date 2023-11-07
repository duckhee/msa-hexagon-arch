package kr.co.won.userservice.domain.modal.event;

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
