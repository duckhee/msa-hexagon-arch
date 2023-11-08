package kr.co.won.userservice.framework.adapter.inbound.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.userservice.application.usecase.SavePointUseCase;
import kr.co.won.userservice.application.usecase.UsePointUseCase;
import kr.co.won.userservice.domain.modal.event.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class MemberEventConsumers {

    private final ObjectMapper objectMapper;
    private final SavePointUseCase savePointUseCase;
    private final UsePointUseCase usePointUseCase;


    public MemberEventConsumers(ObjectMapper objectMapper, SavePointUseCase savePointUseCase, UsePointUseCase usePointUseCase) {
        this.objectMapper = objectMapper;
        this.savePointUseCase = savePointUseCase;
        this.usePointUseCase = usePointUseCase;
    }

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws IOException {
        log.info("[kafka-consumer] rental-event : {}", record.value());
        ItemRented rentEvent = objectMapper.readValue(record.value(), ItemRented.class);
        savePointUseCase.savePoint(rentEvent.getIdName(), rentEvent.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws Exception {
        log.info("[kafka-consumer] return-event : {}", record.value());
        ItemReturned returnEvent = objectMapper.readValue(record.value(), ItemReturned.class);
        savePointUseCase.savePoint(returnEvent.getIdName(), returnEvent.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic3.name}", groupId = "${consumer.groupid.name}")
    public void consumeClearOverdue(ConsumerRecord<String, String> record) throws Exception {
        log.info("[kafka-consumer] clear-overdue-event : {}", record.value());
        OverDueCleared overdueEvent = objectMapper.readValue(record.value(), OverDueCleared.class);
        //
        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.OVERDUE);
        eventResult.setIdName(overdueEvent.getIdName());
        eventResult.setPoint(overdueEvent.getPoint());
        try {
            usePointUseCase.userUsePoint(overdueEvent.getIdName(), overdueEvent.getPoint());
            eventResult.setSuccess(true);
        } catch (Exception exception) {
            log.error("[kafka-consume] use point error : {}", exception);
        }
    }


    @KafkaListener(topics = "${consumer.topic4.name}", groupId = "${consumer.groupid.name}")
    public void consumeUsePoint(ConsumerRecord<String, String> record) throws Exception {
        log.info("[kafka-consumer] get value : {}", record.value().toString());
        PointUseCommand pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand.class);
        log.info("[kafka-consumer] get command : {}", pointUseCommand.toString());

    }

}
