package kr.co.won.userservice.application.usecase;

import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;

import java.util.Optional;

public interface InQueryMemberUseCase {

    default Optional<MemberOutputDto> findMember(long memberNo) {
        return Optional.empty();
    }
}
