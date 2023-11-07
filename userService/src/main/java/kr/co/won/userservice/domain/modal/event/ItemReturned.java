package kr.co.won.userservice.domain.modal.event;


import kr.co.won.userservice.domain.modal.vo.IDName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class ItemReturned extends ItemRented {

    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
