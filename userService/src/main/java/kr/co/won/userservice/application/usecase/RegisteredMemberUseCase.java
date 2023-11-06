package kr.co.won.userservice.application.usecase;

import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberInfoDto;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;

public interface RegisteredMemberUseCase {

    default MemberOutputDto registeredMember(MemberInfoDto memberInfoDto) {
        return null;
    }
}
