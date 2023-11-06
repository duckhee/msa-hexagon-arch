package kr.co.won.bestbookservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestBook {

    @MongoId
    private String id;

    private Item item;

    private long rentCount;

    public static BestBook registeredBestBook(Item item) {
        UUID uuid = UUID.randomUUID();
        BestBook bestBook = new BestBook();
        bestBook.setId(uuid.toString());
        bestBook.setItem(item);
        bestBook.setRentCount(1l);
        return bestBook;
    }

    public long increaseBestBookCount() {
        this.rentCount = this.getRentCount() + 1;
        return this.rentCount;
    }
}


