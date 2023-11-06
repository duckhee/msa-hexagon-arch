package kr.co.won.userservice.application.port.inbound;

import kr.co.won.userservice.application.port.outbound.MemberOutputPort;
import kr.co.won.userservice.application.usecase.InQueryMemberUseCase;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InQueryMemberInputPort implements InQueryMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    public InQueryMemberInputPort(MemberOutputPort memberOutputPort) {
        this.memberOutputPort = memberOutputPort;
    }

    @Override
    public Optional<MemberOutputDto> findMember(long memberNo) {
        return memberOutputPort.loadMember(memberNo).map(MemberOutputDto::mapToDto);
    }
}
