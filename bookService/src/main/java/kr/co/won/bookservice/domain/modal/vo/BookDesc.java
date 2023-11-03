package kr.co.won.bookservice.domain.modal.vo;

import jakarta.persistence.Embeddable;
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

    private Source source;

}
