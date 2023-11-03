package kr.co.won.rentalcardservice.domain.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import kr.co.won.rentalcardservice.domain.model.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReturnItem {

    @Embedded
    private RentalItem rentalItem;

    // 반납일
    private LocalDate returnDate;

    public static ReturnItem createReturnItem(RentalItem rentalItem) {
        return new ReturnItem(rentalItem, LocalDate.now());
    }
}
