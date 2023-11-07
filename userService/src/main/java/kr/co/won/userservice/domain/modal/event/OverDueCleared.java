package kr.co.won.userservice.domain.modal.event;


import kr.co.won.userservice.domain.modal.vo.IDName;
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
