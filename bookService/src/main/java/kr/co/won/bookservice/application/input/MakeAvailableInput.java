package kr.co.won.bookservice.application.input;

import kr.co.won.bookservice.application.output.BookOutputPort;
import kr.co.won.bookservice.application.usecase.MakeAvailableUseCase;
import kr.co.won.bookservice.domain.modal.Book;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MakeAvailableInput implements MakeAvailableUseCase {

    private final BookOutputPort bookOutputPort;

    public MakeAvailableInput(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public BookOutputDto available(Long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));;
        loadBook.makeAvailable();
        // 명시적 삽입
        Book updateBook = bookOutputPort.save(loadBook);
        return BookOutputDto.mapToDTO(loadBook);
    }
}
