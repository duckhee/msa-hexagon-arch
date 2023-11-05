package kr.co.won.userservice.domain.modal;

import kr.co.won.userservice.domain.modal.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long memberNo;

    private IDName idName;

    private Password password;

    private Email email;

    private List<Authority> authorities = new ArrayList<>();

    private Point point;


    public static Member registeredMember(IDName idName, Password password, Email email) {
        Member member = new Member();
        member.setIdName(idName);
        member.setPassword(password);
        member.setEmail(email);
        member.setPoint(Point.createPoint());
        member.authorities.add(Authority.basicRole());
        return member;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public void savePoint(long point) {
        this.point.addPoint(point);
    }

    public void usePoint(long point) throws Exception {
        this.point.removePoint(point);
    }

    public Member login(IDName idName, Password password) {
        return this;
    }

    public void logout(IDName idName) {

    }
}
