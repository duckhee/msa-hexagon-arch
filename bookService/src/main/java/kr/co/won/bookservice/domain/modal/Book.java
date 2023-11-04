package kr.co.won.bookservice.domain.modal;

import jakarta.persistence.*;
import kr.co.won.bookservice.domain.modal.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book")
public class Book {

    @Id
    private Long no;

    private String title;

    @Embedded
    private BookDesc desc;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Enumerated(EnumType.STRING)
    private Location location;

    public static Book enterBook(String title, String author, String isbn, String description, LocalDate publicationDate, Source source, Classification classification, Location location) {
        BookDesc newBookDescription = BookDesc.createBookDesc(author, isbn, description, publicationDate, source);
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setDesc(newBookDescription);
        newBook.setClassification(classification);
        newBook.setBookStatus(BookStatus.ENTERED);
        newBook.setLocation(location);
        return newBook;
    }

    public Book makeAvailable() {
        this.setBookStatus(BookStatus.AVAILABLE);
        return this;
    }

    public Book makeUnAvailable() {
        this.setBookStatus(BookStatus.UNAVAILABLE);
        return this;
    }
}
