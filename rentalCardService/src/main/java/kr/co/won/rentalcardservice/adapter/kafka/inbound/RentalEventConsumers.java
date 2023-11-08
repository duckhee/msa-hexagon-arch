package kr.co.won.rentalcardservice.adapter.kafka.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.rentalcardservice.appilcation.usecase.CompensationUseCase;
import kr.co.won.rentalcardservice.domain.model.event.EventResult;
import kr.co.won.rentalcardservice.domain.model.event.EventType;
import kr.co.won.rentalcardservice.domain.model.event.PointUseCommand;
import kr.co.won.rentalcardservice.domain.model.vo.IDName;
import kr.co.won.rentalcardservice.domain.model.vo.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.logging.LogLevel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RentalEventConsumers {

    private final ObjectMapper objectMapper;
    private final CompensationUseCase compensationUseCase;

    public RentalEventConsumers(ObjectMapper objectMapper, CompensationUseCase compensationUseCase) {
        this.objectMapper = objectMapper;
        this.compensationUseCase = compensationUseCase;

    }

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws Exception {
        try {
            EventResult eventResult = objectMapper.readValue(record.value(), EventResult.class);
            IDName idName = eventResult.getIdName();
            Item item = eventResult.getItem();
            long point = eventResult.getPoint();
            if (!eventResult.isSuccess()) {
                EventType eventType = eventResult.getEventType();
                log.info("[kafka-consumer] get event type : {}", eventType);
                switch (eventType) {
                    case RENT -> compensationUseCase.cancelRentItem(idName, item);
                    case RETURN -> compensationUseCase.cancelReturnItem(idName, item, point);
                    case OVERDUE -> compensationUseCase.cancelMakeAvailableRental(idName, point);
                }
            }
            log.info("[kafka-consumer] get event success : {}", eventResult.isSuccess());
        } catch (Exception exception) {
            log.error("[kafka-consumer] handling failed : {}", exception);
            throw exception;
        }
    }


}
