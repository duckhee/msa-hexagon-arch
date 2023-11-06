package kr.co.won.userservice.domain.modal.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    private long point;

    public long addPoint(long point) {
        this.setPoint(this.point + point);
        return this.point;
    }

    public long removePoint(long point) throws Exception {
        if (point > this.point) {
            throw new IllegalArgumentException("기존 보유 Point 가 적어 삭제할 수 없습니다.");
        }
        this.setPoint(this.point - point);
        return this.point;
    }

    public static Point createPoint() {
        return new Point(0L);
    }
}
