package kr.co.won.userservice.framework.adapter.inbound.web.vo;

import kr.co.won.userservice.domain.modal.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberOutputDto {

    private String id;

    private String name;

    private String email;

    private String password;

    private long point;

    public static MemberOutputDto mapToDto(Member member) {
        MemberOutputDto memberOutputDto = new MemberOutputDto();
        memberOutputDto.setId(member.getIdName().getId());
        memberOutputDto.setName(member.getIdName().getName());
        memberOutputDto.setPassword(member.getPassword().getPastPassword());
        memberOutputDto.setEmail(member.getEmail().getEmail());
        memberOutputDto.setPoint(member.getPoint().getPoint());
        return memberOutputDto;
    }
}
