package kr.co.won.rentalcardservice.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Table;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "tbl_rental_item")
public class RentalItem {

    @Embedded
    private Item item;

    // 대여일
    private LocalDate rentDate;

    public boolean overdue;

    // 반납 예정일
    private LocalDate overdueDate;

    public static RentalItem createRentalItem(Item item) {
        return new RentalItem(item, LocalDate.now(), false, LocalDate.now().plusDays(14));
    }
}
