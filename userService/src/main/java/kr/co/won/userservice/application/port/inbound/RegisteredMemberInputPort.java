package kr.co.won.userservice.application.port.inbound;

import kr.co.won.userservice.application.port.outbound.MemberOutputPort;
import kr.co.won.userservice.application.usecase.RegisteredMemberUseCase;
import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.Email;
import kr.co.won.userservice.domain.modal.vo.IDName;
import kr.co.won.userservice.domain.modal.vo.Password;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberInfoDto;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisteredMemberInputPort implements RegisteredMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    public RegisteredMemberInputPort(MemberOutputPort memberOutputPort) {
        this.memberOutputPort = memberOutputPort;
    }


    @Override
    public MemberOutputDto registeredMember(MemberInfoDto memberInfoDto) {
        IDName idName = new IDName(memberInfoDto.getId(), memberInfoDto.getName());
        Password password = new Password(memberInfoDto.getPassword(), memberInfoDto.getPassword());
        Email email = new Email(memberInfoDto.getEmail());
        Member newMember = Member.registeredMember(idName, password, email);
        Member savedNewMember = memberOutputPort.saveMember(newMember);
        return MemberOutputDto.mapToDto(savedNewMember);
    }
}
