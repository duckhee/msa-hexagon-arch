package kr.co.won.bookservice.application.usecase;

import kr.co.won.bookservice.framework.web.dto.BookInfoDto;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;

public interface AddBookUserCase {

    default BookOutputDto addBook(BookInfoDto bookInfoDto) {
        return null;
    }
}
