package kr.co.won.bookservice.framework.kafka.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.bookservice.application.usecase.MakeAvailableUseCase;
import kr.co.won.bookservice.application.usecase.MakeUnAvailableUseCase;
import kr.co.won.bookservice.domain.modal.event.EventResult;
import kr.co.won.bookservice.domain.modal.event.EventType;
import kr.co.won.bookservice.domain.modal.event.ItemRented;
import kr.co.won.bookservice.domain.modal.event.ItemReturned;
import kr.co.won.bookservice.framework.kafka.outbound.BookEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class BookEventConsumers {

    private final ObjectMapper objectMapper;
    private final MakeAvailableUseCase makeAvailableUseCase;
    private final MakeUnAvailableUseCase makeUnAvailableUseCase;
    private final BookEventProducer bookEventProducer;


    public BookEventConsumers(ObjectMapper objectMapper, MakeAvailableUseCase makeAvailableUseCase, MakeUnAvailableUseCase makeUnAvailableUseCase, BookEventProducer bookEventProducer) {
        this.objectMapper = objectMapper;
        this.makeAvailableUseCase = makeAvailableUseCase;
        this.makeUnAvailableUseCase = makeUnAvailableUseCase;
        this.bookEventProducer = bookEventProducer;
    }

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> recode) throws IOException {
        log.info("[kafka-consume] rental_event : " + recode.value());
        // 이벤트를 받아서 객체 생성
        ItemRented itemRented = objectMapper.readValue(recode.value(), ItemRented.class);
        // 보상 트랜젝션을 구현하기 위한 새로운 이벤트 발행
        EventResult eventResult = new EventResult();
        eventResult.setItem(itemRented.getItem());
        eventResult.setPoint(itemRented.getPoint());
        eventResult.setIdName(itemRented.getIdName());
        eventResult.setEventType(EventType.RENT);
        try {
            // 이벤트 처리
            makeUnAvailableUseCase.unAvailable(itemRented.getItem().getNo());
            eventResult.setSuccess(true);
        } catch (Exception exception) {
            log.error("[kafka-consume] rental_event failed : {}, {}", itemRented.toString(), exception.toString());
            eventResult.setSuccess(false);
        }

        // 성공 이벤트 발행
        bookEventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> recode) throws IOException {
        log.info("[kafka-consume] return_event : " + recode.value());
        // 이벤트를 받아서 객체 생성
        ItemReturned itemReturned = objectMapper.readValue(recode.value(), ItemReturned.class);
        // 이벤트를 받아서 객체 생성
        ItemRented itemRented = objectMapper.readValue(recode.value(), ItemRented.class);
        // 보상 트랜젝션을 구현하기 위한 새로운 이벤트 발행
        EventResult eventResult = new EventResult();
        eventResult.setItem(itemRented.getItem());
        eventResult.setPoint(itemRented.getPoint());
        eventResult.setIdName(itemRented.getIdName());
        eventResult.setEventType(EventType.RETURN);
        try {
            // 이벤트 처리
            makeAvailableUseCase.available(itemReturned.getItem().getNo());
            eventResult.setSuccess(true);
        } catch (Exception exception) {
            log.error("[kafka-consume] rental_event failed : {}, {}", itemRented.toString(), exception.toString());
            eventResult.setSuccess(false);
        }
        // 성공 이벤트 발행
        bookEventProducer.occurEvent(eventResult);
    }

}
