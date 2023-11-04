package kr.co.won.bookservice.framework.web.in;

import kr.co.won.bookservice.application.usecase.AddBookUserCase;
import kr.co.won.bookservice.application.usecase.InQueryUseCase;
import kr.co.won.bookservice.application.usecase.MakeAvailableUseCase;
import kr.co.won.bookservice.application.usecase.MakeUnAvailableUseCase;
import kr.co.won.bookservice.framework.web.dto.BookInfoDto;
import kr.co.won.bookservice.framework.web.dto.BookOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {

    private final AddBookUserCase addBookUserCase;
    private final MakeAvailableUseCase makeAvailableUseCase;
    private final MakeUnAvailableUseCase makeUnAvailableUseCase;
    private final InQueryUseCase inQueryUseCase;

    public BookController(AddBookUserCase addBookUserCase, MakeAvailableUseCase makeAvailableUseCase, MakeUnAvailableUseCase makeUnAvailableUseCase, InQueryUseCase inQueryUseCase) {
        this.addBookUserCase = addBookUserCase;
        this.makeAvailableUseCase = makeAvailableUseCase;
        this.makeUnAvailableUseCase = makeUnAvailableUseCase;
        this.inQueryUseCase = inQueryUseCase;
    }

    @PostMapping
    public ResponseEntity<BookOutputDto> createBookResponse(@RequestBody BookInfoDto bookInfoDto) {
        BookOutputDto response = addBookUserCase.addBook(bookInfoDto);
        URI createdUri = URI.create("/api/book/s" + response.getBookNo());
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping(path = "/{bookNo}")
    public ResponseEntity<BookOutputDto> getBookInformationResponse(@PathVariable(name = "bookNo") Long bookNo) {
        BookOutputDto response = inQueryUseCase.getBookInfo(bookNo).orElse(null);
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));

        return response != null ?ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
