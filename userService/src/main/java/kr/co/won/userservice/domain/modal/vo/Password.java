package kr.co.won.userservice.domain.modal.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    private String presentPassword;

    private String pastPassword;

}
