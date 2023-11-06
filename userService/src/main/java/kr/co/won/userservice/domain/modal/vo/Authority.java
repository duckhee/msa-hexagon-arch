package kr.co.won.userservice.domain.modal.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Enumerated(EnumType.STRING)
    private UserRole roleName;


    public static Authority basicRole() {
        Authority authority = new Authority();
        authority.setRoleName(UserRole.USER);
        return authority;
    }
}
