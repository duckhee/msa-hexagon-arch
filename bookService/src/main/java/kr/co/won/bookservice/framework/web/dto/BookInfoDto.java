package kr.co.won.bookservice.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDto {

    private String title;

    private String description;

    private String author;

    private String isbn;

    private LocalDate publicationDate;

    private String source;

    private String classification;

    private String location;

}
