package kr.co.won.userservice.application.usecase;

import kr.co.won.userservice.domain.modal.vo.IDName;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;

public interface SavePointUseCase {

    default MemberOutputDto savePoint(IDName idName, long point) {
        return null;
    }
}
