package kr.co.won.bestbookservice.persistence;

import kr.co.won.bestbookservice.domain.model.BestBook;
import kr.co.won.bestbookservice.domain.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BestBookRepository extends MongoRepository<BestBook, String> {

    BestBook findBestBookByItem(Item item);
}
