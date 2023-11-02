package kr.co.won.rentalcardservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
/*
    @Bean
    public ProducerFactory<String, ItemRental> producerFactory() {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "");
        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "");
        return new DefaultKafkaProducerFactory<>(configuration);
    }

    @Bean
    public KafkaTemplate kafkaTemplate(ProducerFactory producerFactory){
        return new KafkaTemplate(producerFactory);
    }
*/


}
