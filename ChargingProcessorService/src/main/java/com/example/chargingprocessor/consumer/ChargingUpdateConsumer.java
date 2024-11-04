package com.example.chargingprocessor.consumer;

import com.example.chargingprocessor.entity.ChargingRequest;
import com.example.chargingprocessor.service.ChargingUpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChargingUpdateConsumer {

    @Autowired
    private ChargingUpdateService chargingUpdateService;

    @KafkaListener(id = "charging-update-consumer",
            topics = "charging-status-stream")
    @Retryable(value = { RuntimeException.class }, maxAttempts = 5,
            backoff = @Backoff(delay = 2000))
    public void listen(@Payload String message,
                       @Header(name = "system", required = false) String system,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                       Acknowledgment ack) {
        log.info("Message received: {} from topic: {}, partition: {}, system: {}, " +
                "timestamp: {}", message, topic, partition, system, timestamp);


        chargingUpdateService.UpdateChargingRequestDetails(getChargingRequest(message));
        ack.acknowledge();
    }

    private ChargingRequest getChargingRequest(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(message, ChargingRequest.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
