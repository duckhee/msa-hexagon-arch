package kr.co.won.rentalcardservice.domain.model;

import kr.co.won.rentalcardservice.domain.model.vo.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
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

    /**
     * <p>
     * 대여 카드 생성
     * </p>
     *
     * @param creator
     * @return
     */
    public static RentalCard createRentalCard(IDName creator) {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(creator);
        rentalCard.setRentalStatus(RentalStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.createLateFee());
        return rentalCard;
    }

    // 대여 처리
    public RentalCard rentalCard(Item item) {
        // rental 이 가능하지 여부를 확인하는 로직을 담고 있는 함수
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    // 대여가 가능한 상태인지 상태 확인하는 함수
    private void checkRentalAvailable() {
        if (this.rentalStatus == RentalStatus.RENT_UNAVAILABLE) {
            throw new IllegalArgumentException("대여 불가능한 상태입니다.");
        }
        if (this.rentalItems.size() > 5) {
            throw new IllegalArgumentException("이미 5권을 대여했습니다.");
        }
    }

    // 반납을 하는 로직
    public RentalCard returnItem(Item returnItem, LocalDate returnDate) {
        RentalItem rentalItem = this.rentalItems.stream().filter(item -> item.getItem().equals(returnItem)).findFirst().get();
        // 연체료를 계산하는 함수
        calculateLateFee(rentalItem, returnDate);
        this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    // 연체료를 계산하는 함수
    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        if (returnDate.isAfter(rentalItem.getOverdueDate())) {
            // 연체료 계산
            long point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10;
            // 연체료 객체 생성
            this.lateFee.setPoint(this.lateFee.addPoint(point).getPoint());
        }
    }

    // TODO test 를 위한 함수
    // 연체 처리를 위한 함수
    public RentalCard overdueItem(Item item) {
        RentalItem rentalItem = this.rentalItems.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        rentalItem.setOverdue(true);
        this.rentalStatus = RentalStatus.RENT_UNAVAILABLE;
        // 연체를 생성하는 것 -> test 를 위한 코드
        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    // 연체 해제 하는 함수
    public long makeAvailableRental(long point) {

        if (this.rentalItems.size() != 0) {
            throw new IllegalArgumentException("모든 도서가 반납이 되어야 해제 할 수 있습니다.");
        }
        // TODO checking logic
        if (this.getLateFee().getPoint() != point) {
            throw new IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습니다.");
        }

        this.setLateFee(lateFee.removePoint(point));
        if (this.getLateFee().getPoint() == 0) {
            this.rentalStatus = RentalStatus.RENT_AVAILABLE;
        }
        return this.getLateFee().getPoint();
    }
}
