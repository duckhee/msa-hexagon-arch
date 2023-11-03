package kr.co.won.rentalcardservice.adapter.web.dto;

import kr.co.won.rentalcardservice.domain.model.RentalCard;
import lombok.*;

@Data
@NoArgsConstructor
public class RentalCardOutputDTO {

    private String rentalCardId;

    private String memberId;

    private String memberName;

    private String rentStatus; // 대여 가능 여부

    private Long totalLateFee; // 전체 연체료

    private Long totalRentalCnt; // 전체 대여 도서 건 수

    private Long totalReturnCnt; // 반납 도서 건 수

    private Long totalOverdueCnt; // 연체 중인 도서 건 수

    public static RentalCardOutputDTO mapToDTO(RentalCard rentalCard) {
        RentalCardOutputDTO outputDTO = new RentalCardOutputDTO();
        outputDTO.setRentalCardId(rentalCard.getRentalCardNo().getNo());
        outputDTO.setMemberId(rentalCard.getMember().getId());
        outputDTO.setMemberName(rentalCard.getMember().getName());
        outputDTO.setRentStatus(rentalCard.getRentalStatus().name());
        outputDTO.setTotalRentalCnt(rentalCard.getRentalItems().stream().count());
        outputDTO.setTotalReturnCnt(rentalCard.getReturnItems().stream().count());
        outputDTO.setTotalOverdueCnt(rentalCard.getRentalItems().stream().filter(i -> i.isOverdue()).count());
        return outputDTO;
    }
}
