package kr.co.won.rentalcardservice.domain.model;

import kr.co.won.rentalcardservice.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {

    private RentalCardNo rentalCardNo;

    private IDName member;

    private RentalStatus rentalStatus;

    private LateFee lateFee;

    private List<RentalItem> rentalItems = new ArrayList<>();

    private List<ReturnItem> returnItems = new ArrayList<>();

    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItems.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItems.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItems.add(returnItem);
    }

}
