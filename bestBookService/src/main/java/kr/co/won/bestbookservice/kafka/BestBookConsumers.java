package kr.co.won.bestbookservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.bestbookservice.domain.BestBookService;
import kr.co.won.bestbookservice.domain.event.ItemRented;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BestBookConsumers {

    private final ObjectMapper objectMapper;
    private final BestBookService bestBookService;


    public BestBookConsumers(ObjectMapper objectMapper, BestBookService bestBookService) {
        this.objectMapper = objectMapper;
        this.bestBookService = bestBookService;
    }

    @KafkaListener(topics = "rental_rent", groupId = "bestBook")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("[kafka-consume] rental-event : {} ", record.value());
        ItemRented itemRent = objectMapper.readValue(record.value(), ItemRented.class);
        bestBookService.dealBestBook(itemRent.getItem());
    }
}
