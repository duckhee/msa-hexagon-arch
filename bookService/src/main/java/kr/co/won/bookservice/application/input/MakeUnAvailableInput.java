package kr.co.won.bookservice.application.input;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.application.usecase.MakeUnAvailableUseCase;
import kr.co.won.bookservice.domain.modal.Book;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MakeUnAvailableInput implements MakeUnAvailableUseCase {

    private final BookOutputPort bookOutputPort;

    public MakeUnAvailableInput(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public BookOutputDto unAvailable(Long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo);
        loadBook.makeUnAvailable();
        // 명시적인 로그인
        bookOutputPort.save(loadBook);
        return BookOutputDto.mapToDTO(loadBook);
    }
}
