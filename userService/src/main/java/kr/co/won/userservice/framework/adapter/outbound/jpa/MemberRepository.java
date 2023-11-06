package kr.co.won.userservice.framework.adapter.outbound.jpa;

import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.IDName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberNo(Long id);

    Optional<Member> findMemberByIdName(IDName idName);
}
