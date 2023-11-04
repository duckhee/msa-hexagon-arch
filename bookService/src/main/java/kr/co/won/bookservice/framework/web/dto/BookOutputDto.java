package kr.co.won.bookservice.framework.web.dto;

import kr.co.won.bookservice.domain.modal.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOutputDto {

    private long bookNo;

    private String bookTitle;

    private String bookStatus;

    public static BookOutputDto mapToDTO(Book book) {
        BookOutputDto bookOutputDto = new BookOutputDto();
        bookOutputDto.setBookNo(book.getNo());
        bookOutputDto.setBookTitle(book.getTitle());
        bookOutputDto.setBookStatus(book.getBookStatus().name());
        return bookOutputDto;
    }
}
