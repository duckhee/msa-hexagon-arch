package kr.co.won.rentalcardservice.domain.model.event;

import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OverDueCleared implements Serializable {

    private IDName idName;

    private long point;
}
