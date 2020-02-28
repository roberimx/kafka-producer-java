package me.tecuani.kafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KafkaHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaHandlerApplication.class, args);
    }

}
