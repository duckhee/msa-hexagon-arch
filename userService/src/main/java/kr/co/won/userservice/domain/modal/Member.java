package kr.co.won.userservice.domain.modal;

import jakarta.persistence.*;
import kr.co.won.userservice.domain.modal.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Embedded
    private IDName idName;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @ElementCollection
    private List<Authority> authorities = new ArrayList<>();

    @Embedded
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
