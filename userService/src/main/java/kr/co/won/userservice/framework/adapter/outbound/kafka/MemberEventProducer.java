package kr.co.won.userservice.framework.adapter.outbound.kafka;

import kr.co.won.userservice.domain.modal.event.EventResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class MemberEventProducer {

    @Value("${producers.topic1.name}")
    private String TOPIC;

    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public MemberEventProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void occurEvent(EventResult eventResult) throws Exception {
        CompletableFuture<SendResult<String, EventResult>> future = kafkaTemplate.send(TOPIC, eventResult);

        future.whenComplete((result, exception) -> {
            if (result != null) {
                log.info("[kafka-producer] member event result : {} offset : {}", result.getProducerRecord().value().toString(), result.getRecordMetadata().offset());
            }

            if (exception != null) {
                log.error("[kafka-producer] unable send message : {} error : {}", eventResult.getEventType(), exception);
            }
        });

    }

}
