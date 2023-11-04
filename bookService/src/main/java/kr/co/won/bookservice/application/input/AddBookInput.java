package kr.co.won.bookservice.application.input;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.application.usecase.AddBookUserCase;
import kr.co.won.bookservice.domain.modal.Book;
import kr.co.won.bookservice.domain.modal.vo.Classification;
import kr.co.won.bookservice.domain.modal.vo.Location;
import kr.co.won.bookservice.domain.modal.vo.Source;
import kr.co.won.bookservice.framework.web.dto.BookInfoDto;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddBookInput implements AddBookUserCase {

    private final BookOutputPort bookOutputPort;


    public AddBookInput(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public BookOutputDto addBook(BookInfoDto bookInfoDto) {
        Book book = Book.enterBook(bookInfoDto.getTitle(), bookInfoDto.getAuthor(), bookInfoDto.getIsbn(), bookInfoDto.getDescription(), bookInfoDto.getPublicationDate(), Source.valueOf(bookInfoDto.getSource()), Classification.valueOf(bookInfoDto.getClassification()), Location.valueOf(bookInfoDto.getLocation()));
        Book savedBook = bookOutputPort.save(book);
        return BookOutputDto.mapToDTO(savedBook);
    }
}
