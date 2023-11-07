package kr.co.won.bookservice.framework.kafka.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.bookservice.application.usecase.MakeAvailableUseCase;
import kr.co.won.bookservice.application.usecase.MakeUnAvailableUseCase;
import kr.co.won.bookservice.domain.modal.event.ItemRented;
import kr.co.won.bookservice.domain.modal.event.ItemReturned;
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

    public BookEventConsumers(ObjectMapper objectMapper, MakeAvailableUseCase makeAvailableUseCase, MakeUnAvailableUseCase makeUnAvailableUseCase) {
        this.objectMapper = objectMapper;
        this.makeAvailableUseCase = makeAvailableUseCase;
        this.makeUnAvailableUseCase = makeUnAvailableUseCase;
    }

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> recode) throws IOException {
        log.info("[kafka-consume] rental_event : " + recode.value());
        //
        ItemRented itemRented = objectMapper.readValue(recode.value(), ItemRented.class);
        //
        makeUnAvailableUseCase.unAvailable(itemRented.getItem().getNo());
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> recode) throws IOException {
        log.info("[kafka-consume] return_event : " + recode.value());
        //
        ItemReturned itemReturned = objectMapper.readValue(recode.value(), ItemReturned.class);
        //
        makeAvailableUseCase.available(itemReturned.getItem().getNo());
    }
}
