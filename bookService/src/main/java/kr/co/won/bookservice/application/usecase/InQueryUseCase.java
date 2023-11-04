package kr.co.won.bookservice.application.usecase;

import kr.co.won.bookservice.framework.web.dto.BookOutputDto;

import java.util.Optional;

public interface InQueryUseCase {

    default Optional<BookOutputDto> getBookInfo(long bookNo) {
        return null;
    }
}
