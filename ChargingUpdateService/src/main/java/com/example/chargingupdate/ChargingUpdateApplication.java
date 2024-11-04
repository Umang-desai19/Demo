package com.example.chargingupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
public class ChargingUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChargingUpdateApplication.class, args);
	}

}
