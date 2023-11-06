package kr.co.won.userservice.application.port.inbound;

import kr.co.won.userservice.application.port.outbound.MemberOutputPort;
import kr.co.won.userservice.application.usecase.SavePointUseCase;
import kr.co.won.userservice.domain.modal.Member;
import kr.co.won.userservice.domain.modal.vo.IDName;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SavePointInputPort implements SavePointUseCase {

    private final MemberOutputPort memberOutputPort;

    public SavePointInputPort(MemberOutputPort memberOutputPort) {
        this.memberOutputPort = memberOutputPort;
    }

    @Override
    public MemberOutputDto savePoint(IDName idName, long point) {
        Member findMember = memberOutputPort.loadMemberByIdName(idName)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 사용자는 존재 하지 않습니다."));
        findMember.savePoint(point);
        // 명시적으로 저장
        memberOutputPort.saveMember(findMember);
        return MemberOutputDto.mapToDto(findMember);
    }
}
