package kr.co.won.bookservice.application.usecase;

import kr.co.won.bookservice.framework.web.dto.BookOutputDto;

public interface InQueryUseCase {

    default BookOutputDto getBookInfo(long bookNo) {
        return null;
    }
}
