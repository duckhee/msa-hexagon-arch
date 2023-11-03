package kr.co.won.bookservice.domain.modal;

import jakarta.persistence.*;
import kr.co.won.bookservice.domain.modal.vo.BookDesc;
import kr.co.won.bookservice.domain.modal.vo.BookStatus;
import kr.co.won.bookservice.domain.modal.vo.Classification;
import kr.co.won.bookservice.domain.modal.vo.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
