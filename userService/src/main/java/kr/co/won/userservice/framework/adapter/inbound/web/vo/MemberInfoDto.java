package kr.co.won.userservice.framework.adapter.inbound.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {

    private String id;

    private String name;

    private String email;

    private String password;
}
