package me.tecuani.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    private final AppProperties appProperties;

    public KafkaConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(appProperties.getTopic())
                .partitions(appProperties.getPartitions())
                .replicas(appProperties.getReplicas())
                .build();
    }

}
