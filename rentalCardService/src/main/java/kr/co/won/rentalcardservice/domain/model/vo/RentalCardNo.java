package kr.co.won.rentalcardservice.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCardNo {

    private String no;

    /**
     * <p>
     * 식별자를 생성 해주는 함수
     * </p>
     *
     * @return
     */
    public static RentalCardNo createRentalCardNo() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String privateKey = year + "-" + uuid;
        RentalCardNo rentalCardNo = new RentalCardNo();
        rentalCardNo.setNo(privateKey);
        return rentalCardNo;
    }


}
