package kr.co.won.bookservice.framework.kafka.outbound;


import kr.co.won.bookservice.domain.modal.event.EventResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BookEventProducer {

    @Value("${producers.topic1.name}")
    private String TOPIC;

    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public BookEventProducer(KafkaTemplate<String, EventResult> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void occurEvent(EventResult eventResult) {
        CompletableFuture<SendResult<String, EventResult>> future = this.kafkaTemplate.send(TOPIC, eventResult);
        // 전달 결과에 대해서 처리하는 것
        future.whenComplete((result, exception) -> {
            // 성공 시 처리
            if (result != null) {
                // 전달한 객체에 대해서 확인을 위한 값 확인
                EventResult eventSendValue = result.getProducerRecord().value();
                log.info("[kafka-producers] producer send success : {}", eventSendValue.toString());
            }
            // 실패 시 처리
            if (exception != null) {
                // 전달 실패에 대한 로그 확인
                log.error("[kafka-producers] producer send failed : {} exception : {}", eventResult.toString(), exception.toString());
            }
        });
    }
}
