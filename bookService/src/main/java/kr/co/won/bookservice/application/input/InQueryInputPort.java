package kr.co.won.bookservice.application.input;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.application.usecase.InQueryUseCase;
import kr.co.won.bookservice.domain.modal.Book;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InQueryInputPort implements InQueryUseCase {

    private final BookOutputPort bookOutputPort;

    public InQueryInputPort(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public Optional<BookOutputDto> getBookInfo(long bookNo) {
        Optional<BookOutputDto> bookOutputDto = bookOutputPort.loadBook(bookNo).map(BookOutputDto::mapToDTO);
        return bookOutputDto;
    }
}
