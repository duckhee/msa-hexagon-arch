package kr.co.won.bookservice.application.usecase;

import kr.co.won.bookservice.framework.web.dto.BookOutputDto;

public interface MakeUnAvailableUseCase {

    default BookOutputDto unAvailable(Long bookNo) {
        return null;
    }
}
