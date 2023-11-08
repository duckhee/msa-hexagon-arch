package kr.co.won.rentalcardservice.adapter.kafka.outbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.won.rentalcardservice.appilcation.port.out.EventOutputPort;
import kr.co.won.rentalcardservice.domain.model.event.ItemRented;
import kr.co.won.rentalcardservice.domain.model.event.ItemReturned;
import kr.co.won.rentalcardservice.domain.model.event.OverDueCleared;
import kr.co.won.rentalcardservice.domain.model.event.PointUseCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class RentalKafkaProducer implements EventOutputPort {

    @Value("${producers.topic1.name}")
    private String TOPIC_RENT;
    @Value("${producers.topic2.name")
    private String TOPIC_RETURN;

    @Value("${producers.topic3.name}")
    private String TOPIC_OVER_DUE_CLEAR;

    @Value("${producers.topic4.name}")
    private String TOPIC_POINT;

    private final KafkaTemplate<String, ItemRented> rentalKafka;
    private final KafkaTemplate<String, ItemReturned> returnKafka;
    private final KafkaTemplate<String, OverDueCleared> overdueClearKafka;

    private final KafkaTemplate<String, PointUseCommand> pointKafkaCommand;

    public RentalKafkaProducer(KafkaTemplate<String, ItemRented> rentalKafka, KafkaTemplate<String, ItemReturned> returnKafka, KafkaTemplate<String, OverDueCleared> overdueClearKafka, KafkaTemplate<String, PointUseCommand> pointKafkaCommand) {
        this.rentalKafka = rentalKafka;
        this.returnKafka = returnKafka;
        this.overdueClearKafka = overdueClearKafka;
        this.pointKafkaCommand = pointKafkaCommand;
    }

    @Override
    public void occurRentalEvent(ItemRented rentedEvent) throws JsonProcessingException {
        // kafka 에서 전달 후 반환 값을 가져오는 것
        CompletableFuture<SendResult<String, ItemRented>> future = rentalKafka.send(TOPIC_RENT, rentedEvent);
        // TODO checking add callback
        future.whenComplete((result, throwable) -> {
            // result 성공시
            if (result != null) {
                ItemRented resultItem = result.getProducerRecord().value();
                log.info("[kafka-success] send message = [" + resultItem.getItem().getNo() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            // error  발생 시 throwable
            if (throwable != null) {
                log.error("[kafka-error]Unable to send message = [" + rentedEvent.getItem().getNo() + "] due to : " + throwable.getMessage(), throwable);
            }
        });

    }

    @Override
    public void occurReturnEvent(ItemReturned itemReturnedEvent) throws JsonProcessingException {
        CompletableFuture<SendResult<String, ItemReturned>> future = returnKafka.send(TOPIC_RETURN, itemReturnedEvent);
        future.whenComplete((result, throwable) -> {
            // result 성공시
            if (result != null) {
                ItemReturned resultItem = result.getProducerRecord().value();
                log.info("[kafka-success] send message = [" + resultItem.getItem().getNo() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            // error  발생 시 throwable
            if (throwable != null) {
                log.error("[kafka-error]Unable to send message = [" + itemReturnedEvent.getItem().getNo() + "] due to : " + throwable.getMessage(), throwable);
            }
        });
    }

    @Override
    public void occurOverdueClearEvent(OverDueCleared overDueCleared) throws JsonProcessingException {
        CompletableFuture<SendResult<String, OverDueCleared>> future = overdueClearKafka.send(TOPIC_OVER_DUE_CLEAR, overDueCleared);
        future.whenComplete((result, throwable) -> {
            // result 성공시
            if (result != null) {
                OverDueCleared resultItem = result.getProducerRecord().value();
                log.info("[kafka-success] send message = [" + resultItem.getIdName().getId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            // error  발생 시 throwable
            if (throwable != null) {
                log.error("[kafka-error]Unable to send message = [" + overDueCleared.getIdName().getId() + "] due to : " + throwable.getMessage(), throwable);
            }
        });
    }

    @Override
    public void occurPointUseCommand(PointUseCommand pointUseCommand) {
        CompletableFuture<SendResult<String, PointUseCommand>> future = pointKafkaCommand.send(TOPIC_POINT, pointUseCommand);
        future.whenComplete((result, exception) -> {
            if (result != null) {
                PointUseCommand sendResult = result.getProducerRecord().value();
                log.info("[kafka-producer] send message = [" + sendResult.toString() + "], with offset = [" + result.getRecordMetadata().offset() + "]");
            }
            if (exception != null) {
                log.error("[kafka-producer] unable to send message = [" + pointUseCommand.getIdName().getId() + "] due to : {}", exception.toString());
            }
        });
    }
}
