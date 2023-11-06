package kr.co.won.userservice.application.port.inbound;

import kr.co.won.userservice.application.port.outbound.MemberOutputPort;
import kr.co.won.userservice.application.usecase.UsePointUseCase;
import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.IDName;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserUsedPointInputPort implements UsePointUseCase {

    private final MemberOutputPort memberOutputPort;

    public UserUsedPointInputPort(MemberOutputPort memberOutputPort) {
        this.memberOutputPort = memberOutputPort;
    }

    @Override
    public MemberOutputDto userUsePoint(IDName idName, long point) throws Exception {
        Member findMember = memberOutputPort.loadMemberByIdName(idName)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재 하지 않습니다."));
        findMember.usePoint(point);
        // 명시적 저장
        memberOutputPort.saveMember(findMember);
        return MemberOutputDto.mapToDto(findMember);
    }
}
