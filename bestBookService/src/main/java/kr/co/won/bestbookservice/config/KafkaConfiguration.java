package kr.co.won.bestbookservice.config;

import org.springframework.context.annotation.Configuration;

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
