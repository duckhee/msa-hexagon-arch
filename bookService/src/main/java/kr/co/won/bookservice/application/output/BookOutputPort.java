package kr.co.won.bookservice.application.output;

import kr.co.won.bookservice.domain.modal.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputPort {

    default Book loadBook(long bookNo) {
        return null;
    }

    default Book save(Book book) {
        return null;
    }
}
