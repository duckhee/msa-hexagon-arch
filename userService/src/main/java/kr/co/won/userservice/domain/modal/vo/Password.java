package kr.co.won.userservice.domain.modal.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    private String presentPassword;

    private String pastPassword;

}
