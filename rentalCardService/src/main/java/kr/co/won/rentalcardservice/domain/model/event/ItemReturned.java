package kr.co.won.rentalcardservice.domain.model.event;

import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ItemReturned extends ItemRented {
    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
