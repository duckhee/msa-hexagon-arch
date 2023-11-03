package kr.co.won.rentalcardservice.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LateFee {

    private long point;

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(long point) {
        if (point > this.point) {
            throw new IllegalArgumentException("보유한 포인트보다 커서 삭감할 수 없습니다.");
        }
        return new LateFee(this.point - point);
    }

    public static LateFee createLateFee() {
        return new LateFee(0);
    }
}
