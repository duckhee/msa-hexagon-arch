package kr.co.won.bestbookservice.web;

import kr.co.won.bestbookservice.domain.BestBookService;
import kr.co.won.bestbookservice.domain.model.BestBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/best-books")
public class BestBookController {

    private final BestBookService bestBookService;

    public BestBookController(BestBookService bestBookService) {
        this.bestBookService = bestBookService;
    }

    @GetMapping
    public ResponseEntity<List<BestBook>> getAllBestBookResponse() {
        List<BestBook> bestBooks = bestBookService.getAllBooks();
        return ResponseEntity.ok(bestBooks);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BestBook> findBestBookByIdResponse(@PathVariable(name = "id") String id) {
        BestBook bestBook = bestBookService.getBookById(id).orElse(null);
        if (bestBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bestBook);
    }

    @PostMapping
    public ResponseEntity<BestBook> createBestBookResponse(@RequestBody BestBook book) {
        BestBook newBestBook = bestBookService.saveBook(book);
        URI uri = URI.create("/api/best-books/" + newBestBook.getId());
        return ResponseEntity.created(uri).body(newBestBook);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BestBook> updateBestBookResponse(@PathVariable(name = "id") String id, @RequestBody BestBook book) {
        BestBook updateBestBook = bestBookService.updateBook(id, book);
        if (updateBestBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateBestBook);
    }
}
