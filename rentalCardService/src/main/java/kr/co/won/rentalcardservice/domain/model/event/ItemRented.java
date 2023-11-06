package kr.co.won.rentalcardservice.domain.model.event;

import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ItemRented implements Serializable {

    private IDName idName;

    private Item item;

    private long point;
    
}
