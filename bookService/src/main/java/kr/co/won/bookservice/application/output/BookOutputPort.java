package kr.co.won.bookservice.application.output;

import kr.co.won.bookservice.domain.modal.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookOutputPort {

    default Optional<Book> loadBook(long bookNo) {
        return null;
    }

    default Book save(Book book) {
        return null;
    }
}
