package kr.co.won.rentalcardservice.domain.model;

import jakarta.persistence.*;
import kr.co.won.rentalcardservice.domain.model.event.ItemRented;
import kr.co.won.rentalcardservice.domain.model.event.ItemReturned;
import kr.co.won.rentalcardservice.domain.model.event.OverDueCleared;
import kr.co.won.rentalcardservice.domain.model.vo.*;
import lombok.*;

import java.net.IDN;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_rental_card")
public class RentalCard {

    @EmbeddedId
    private RentalCardNo rentalCardNo;

    @Embedded
    private IDName member;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @Embedded
    private LateFee lateFee;

    @ElementCollection
    @JoinTable(name = "tbl_rental_item")
    private List<RentalItem> rentalItems = new ArrayList<>();

    @ElementCollection
    @JoinTable(name = "tbl_return_item")
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

    private void removeReturnItem(ReturnItem returnItem) {
        this.returnItems.remove(returnItem);
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
    public RentalCard rentItem(Item item) {
        // rental 이 가능하지 여부를 확인하는 로직을 담고 있는 함수
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    // 대여 취소 처리
    public RentalCard cancelRentItem(Item item) {
        RentalItem rentalItem = this.rentalItems.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        this.rentalItems.remove(rentalItem);
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

    // 반납에 대한 취소 처리
    public RentalCard cancelReturnItem(Item returnItem) {
        ReturnItem canceldReturnItem = this.returnItems.stream().filter(item -> item.equals(returnItem)).findFirst().get();
        // 다시 대여 처리로 변환
        this.rentalItems.add(canceldReturnItem.getRentalItem());
        // 대여 하는 곳에서 삭제
        this.removeReturnItem(canceldReturnItem);
        return this;
    }

    // 반납에 대한 취소 처리
    public RentalCard cancelReturnItem(Item returnItem, long point) {
        ReturnItem canceldReturnItem = this.returnItems.stream().filter(item -> item.equals(returnItem)).findFirst().get();
        // 다시 대여 처리로 변환
        this.rentalItems.add(canceldReturnItem.getRentalItem());
        // 대여 하는 곳에서 삭제
        this.removeReturnItem(canceldReturnItem);
        // 다시 포인트를 올리고, 대여 가능 여부를 변경한다.
        this.cancelMakeAvailableRental(point);
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

    // 포인트만큼 연체료를 넣어주는 함수
    public long cancelMakeAvailableRental(long point) {
        this.setLateFee(lateFee.addPoint(point));
        this.rentalStatus = RentalStatus.RENT_UNAVAILABLE;
        return this.lateFee.getPoint();
    }

    // 책을 대여를 했을 때, 발행이 되는 이벤트 생성 -> 생성에 대한 책임은 aggregate 에 있으므로 여기서 생성한다.
    public static ItemRented createItemRentalEvent(IDName idName, Item item, long point) {
        return new ItemRented(idName, item, point);
    }

    public static ItemReturned createItemReturnEvent(IDName idName, Item item, long point) {
        return new ItemReturned(idName, item, point);
    }

    public static OverDueCleared createOverdueClearEvent(IDName idName, long point) {
        return new OverDueCleared(idName, point);
    }
}
