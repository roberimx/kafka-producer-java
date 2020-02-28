package me.tecuani.kafkaproducer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("me.tecuani.kafkaproducer")
@Data
public class AppProperties {
    private String topic = "me.tecuani.demo.kafka.topic";
    private int partitions = 2;
    private int replicas = 1;
}
