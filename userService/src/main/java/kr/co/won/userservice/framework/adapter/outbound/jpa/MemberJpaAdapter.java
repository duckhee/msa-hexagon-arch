package kr.co.won.userservice.framework.adapter.outbound.jpa;

import kr.co.won.userservice.application.port.outbound.MemberOutputPort;
import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.IDName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberJpaAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    public MemberJpaAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> loadMember(long memberId) {
        return memberRepository.findByMemberNo(memberId);
    }

    @Override
    public Optional<Member> loadMemberByIdName(IDName idName) {
        return memberRepository.findMemberByIdName(idName);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
