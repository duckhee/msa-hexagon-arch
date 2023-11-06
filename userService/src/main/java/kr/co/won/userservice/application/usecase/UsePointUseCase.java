package kr.co.won.userservice.application.usecase;

import kr.co.won.userservice.domain.modal.vo.IDName;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;

public interface UsePointUseCase {

    default MemberOutputDto userUsePoint(IDName idName, long point) throws Exception {
        return null;
    }
}
