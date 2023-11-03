package kr.co.won.rentalcardservice.adapter.web.dto;

import kr.co.won.rentalcardservice.domain.model.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RentItemOutputDTO {

    private Integer itemNo;
    private String itemTitle;

    private LocalDate rentDate;
    private boolean overdue;
    //반납 예정일
    private LocalDate overdueDate;

    public static RentItemOutputDTO mapToDTO(RentalItem rentItem) {
        RentItemOutputDTO rentItemOutputDTO = new RentItemOutputDTO();
        rentItemOutputDTO.setItemNo(rentItem.getItem().getNo());
        rentItemOutputDTO.setItemTitle(rentItem.getItem().getTitle());
        rentItemOutputDTO.setRentDate(rentItem.getRentDate());
        rentItemOutputDTO.setOverdue(rentItem.isOverdue());
        rentItemOutputDTO.setOverdueDate(rentItem.getOverdueDate());
        return rentItemOutputDTO;
    }
}