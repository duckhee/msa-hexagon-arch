package kr.co.won.bookservice.framework.jpa;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.domain.modal.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookAdapter implements BookOutputPort {

    private final BookRepository bookRepository;

    public BookAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> loadBook(long bookNo) {
        return bookRepository.findByNo(bookNo);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
