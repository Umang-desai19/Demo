package com.example.chargingupdate.service;

import com.example.chargingupdate.dto.ChargingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ChargingUpdateProducerService {

    @Autowired
    private KafkaTemplate<String, ChargingRequest> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topicName;

    public void sendMessage(ChargingRequest chargingRequest) {
        String key = chargingRequest.getCarId() + chargingRequest.getStatus()
                + LocalDateTime.now();
        log.info("Sending message {} to {} with key {}", chargingRequest.getCarId(), topicName, key);
        kafkaTemplate.send(topicName, key, chargingRequest);
    }
}
