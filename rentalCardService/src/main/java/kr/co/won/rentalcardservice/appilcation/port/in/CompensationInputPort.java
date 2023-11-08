package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.appilcation.port.out.EventOutputPort;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.CompensationUseCase;
import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.event.PointUseCommand;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompensationInputPort implements CompensationUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    private final EventOutputPort eventOutputPort;

    public CompensationInputPort(RentalCardOutputPort rentalCardOutputPort, EventOutputPort eventOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
        this.eventOutputPort = eventOutputPort;
    }

    @Override
    public RentalCard cancelRentItem(IDName idName, Item item) {
        RentalCard rentalCardResult = rentalCardOutputPort.loadRentalCard(idName.getId()).map(rentalCard -> {
                    try {
                        rentalCard.cancelReturnItem(item);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, 10l));
                        return rentalCard;
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
        ).orElseThrow(() -> new IllegalArgumentException("해당되는 사용자가 존재하지 않습니다." + idName.getId()));
        return rentalCardResult;
    }

    @Override
    public RentalCard cancelReturnItem(IDName idName, Item item, long point) throws Exception {
        RentalCard rentalCardResult = rentalCardOutputPort.loadRentalCard(idName.getId()).map(rentalCard -> {
                    try {
                        rentalCard.cancelReturnItem(item);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, point));
                        return rentalCard;
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
        ).orElseThrow(() -> new IllegalArgumentException("해당되는 사용자가 존재하지 않습니다." + idName.getId()));
        return rentalCardResult;
    }

    @Override
    public long cancelMakeAvailableRental(IDName idName, long point) {
        long rentalCardResult = rentalCardOutputPort.loadRentalCard(idName.getId()).map(rentalCard -> {
                    try {
                        return rentalCard.cancelMakeAvailableRental(point);
                        // 별도로 포인트 사용 취소를 보상하기 위한 이벤트를 방행하지 않음
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
        ).orElseThrow(() -> new IllegalArgumentException("해당되는 사용자가 존재하지 않습니다." + idName.getId()));
        return rentalCardResult;
    }
}
