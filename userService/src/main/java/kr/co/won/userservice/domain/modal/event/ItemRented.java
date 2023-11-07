package kr.co.won.userservice.domain.modal.event;


import kr.co.won.userservice.domain.modal.vo.IDName;
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
