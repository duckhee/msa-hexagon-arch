package kr.co.won.bookservice.domain.modal.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookDesc {

    private String description;

    private String author;

    private String isbn;

    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    private Source source;


    public static BookDesc createBookDesc(String author, String isbn, String description, LocalDate publicationDate, Source source) {
        return new BookDesc(description, author, isbn, publicationDate, source);
    }
}
