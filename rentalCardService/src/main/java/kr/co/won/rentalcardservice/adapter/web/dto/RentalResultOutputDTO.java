package kr.co.won.rentalcardservice.adapter.web.dto;

import kr.co.won.rentalcardservice.domain.model.RentalCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResultOutputDTO {

    private String userId;

    private String userName;

    private Integer rentedCount;

    private long totalLateFee;

    public static RentalResultOutputDTO matToDTO(RentalCard rentalCard) {
        RentalResultOutputDTO outputDTO = new RentalResultOutputDTO();
        outputDTO.setUserId(rentalCard.getMember().getId());
        outputDTO.setUserName(rentalCard.getMember().getName());
        outputDTO.setRentedCount(rentalCard.getRentalItems().size());
        outputDTO.setTotalLateFee(rentalCard.getLateFee().getPoint());
        return outputDTO;
    }
}
