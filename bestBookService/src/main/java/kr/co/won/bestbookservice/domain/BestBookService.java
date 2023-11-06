package kr.co.won.bestbookservice.domain;

import kr.co.won.bestbookservice.domain.model.BestBook;
import kr.co.won.bestbookservice.domain.model.Item;
import kr.co.won.bestbookservice.persistence.BestBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BestBookService {

    private final BestBookRepository bestBookRepository;

    public BestBookService(BestBookRepository bestBookRepository) {
        this.bestBookRepository = bestBookRepository;
    }

    public List<BestBook> getAllBooks() {
        return bestBookRepository.findAll();
    }

    public Optional<BestBook> getBookById(String id) {
        return bestBookRepository.findById(id);
    }

    public void dealBestBook(Item item) {
        BestBook bestBook = bestBookRepository.findBestBookByItem(item);
        if (bestBook != null) {
            bestBook.increaseBestBookCount();
        } else {
            bestBook.registeredBestBook(item);
        }
        saveBook(bestBook);
    }

    public BestBook updateBook(String id, BestBook book) {
        Optional<BestBook> existingBookOptional = bestBookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            BestBook existingBook = existingBookOptional.get();
            existingBook.setItem(book.getItem());
            existingBook.setRentCount(book.getRentCount());
            return bestBookRepository.save(existingBook);
        }
        return null;
    }

    public BestBook saveBook(BestBook book) {
        return bestBookRepository.save(book);
    }
}
