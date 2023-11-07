package kr.co.won.bestbookservice.domain.event;


import kr.co.won.bestbookservice.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemRented implements Serializable {

    private IDName idName;
    private Item item;
    private long point;
}
