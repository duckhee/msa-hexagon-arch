package kr.co.won.rentalcardservice.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RentalCardNo implements Serializable {

    private static final long serialVersionUID = -1107520421081907987L;

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
