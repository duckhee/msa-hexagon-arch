package kr.co.won.bookservice.application.usecase;

import kr.co.won.bookservice.framework.web.dto.BookOutputDto;

public interface MakeAvailableUseCase {

    default BookOutputDto available(Long bookNo) {
        return null;
    }
}
