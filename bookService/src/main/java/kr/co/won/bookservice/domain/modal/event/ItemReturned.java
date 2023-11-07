package kr.co.won.bookservice.domain.modal.event;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class ItemReturned extends ItemRented {

    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
