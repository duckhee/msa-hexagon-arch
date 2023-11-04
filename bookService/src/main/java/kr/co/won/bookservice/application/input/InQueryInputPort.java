package kr.co.won.bookservice.application.input;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.application.usecase.InQueryUseCase;
import kr.co.won.bookservice.domain.modal.Book;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InQueryInputPort implements InQueryUseCase {

    private final BookOutputPort bookOutputPort;

    public InQueryInputPort(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public BookOutputDto getBookInfo(long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo);
        return BookOutputDto.mapToDTO(loadBook);
    }
}
