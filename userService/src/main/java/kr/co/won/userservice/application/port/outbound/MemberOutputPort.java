package kr.co.won.userservice.application.port.outbound;

import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.IDName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberOutputPort {

    default Optional<Member> loadMember(long memberId) {
        return Optional.empty();
    }

    default Optional<Member> loadMemberByIdName(IDName idName) {
        return Optional.empty();
    }

    default Member saveMember(Member member) {
        return null;
    }
}
