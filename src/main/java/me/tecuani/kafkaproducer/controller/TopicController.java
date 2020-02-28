package me.tecuani.kafkaproducer.controller;

import lombok.extern.java.Log;
import me.tecuani.kafkaproducer.config.AppProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@Log
@RestController
public class TopicController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AppProperties appProperties;
    private final Listener listener;

    public TopicController(KafkaTemplate<String, String> kafkaTemplate, AppProperties appProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.appProperties = appProperties;
        this.listener = new Listener();
    }

    @GetMapping("/send/{msg}")
    public void handle(@PathVariable("msg") String msg) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(appProperties.getTopic(), msg);
        log.log(Level.INFO, String.format("Sending: %s %s", appProperties.getTopic(), msg));
        send.addCallback(this.listener);
    }

    @Log
    private static class Listener implements ListenableFutureCallback<SendResult<String, String>> {

        @Override
        public void onFailure(Throwable ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }

        @Override
        public void onSuccess(SendResult<String, String> result) {
            log.log(Level.INFO, String.format("%s", result));
        }
    }
}
