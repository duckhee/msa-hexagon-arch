package kr.co.won.userservice.domain.modal.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    private UserRole roleName;


    public static Authority basicRole(){
        Authority authority = new Authority();
        authority.setRoleName(UserRole.USER);
        return authority;
    }
}
