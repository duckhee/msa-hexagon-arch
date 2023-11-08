package kr.co.won.userservice.domain.modal.event;

import kr.co.won.userservice.domain.modal.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointUseCommand {

    private IDName idName;

    private long point;

}
