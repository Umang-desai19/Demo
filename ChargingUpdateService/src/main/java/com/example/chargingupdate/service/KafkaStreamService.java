package com.example.chargingupdate.service;

import com.example.chargingupdate.dto.ChargingRequest;
import com.example.chargingupdate.utils.JavaDeserializer;
import com.example.chargingupdate.utils.JavaSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamService {

    @Value("${spring.kafka.topic}")
    private String inputTopic;

    @Value("${spring.kafka.streams.topic}")
    private String processedTopic;


    @Bean
    public KStream<String, ChargingRequest> processStream(StreamsBuilder streamsBuilder) {

        KStream<String, ChargingRequest> stream = streamsBuilder
                .stream(inputTopic, Consumed.with(Serdes.String(), getSreamRequestSerde()));

        stream.filter((k,v) -> v.getStatus().equalsIgnoreCase("CHARGING_STARTED"))
                .mapValues((k,v) -> {
                    v.setStartTime(LocalDateTime.now());
                    v.setEndTime(v.getStartTime().plusMinutes(v.getDuration()));
                    return v;
        });
        stream.mapValues((k,v) -> {
            //v.setRemainingTime(ChronoUnit.MINUTES.between(v.getStartTime(), v.getEndTime()));
            v.setRemainingTime(10L);
            return v;
        });
        stream.filter((k,v) -> v.getPercentComplete() > 0 && v.getPercentComplete() < 30)
                .mapValues((k,v) -> {v.setBatteryStatus("LOW");
                    return v;
                });
        stream.filter((k,v) -> v.getPercentComplete() >= 30 && v.getPercentComplete() < 70)
                .mapValues((k,v) -> {v.setBatteryStatus("MEDIUM");
                    return v;
                });
        stream.filter((k,v) -> v.getPercentComplete() >= 70 && v.getPercentComplete() <= 99)
                .mapValues((k,v) -> {v.setBatteryStatus("HIGH");
                    return v;
                });
        stream.filter((k,v) -> v.getPercentComplete() == 100)
                .mapValues((k,v) -> {v.setBatteryStatus("FULL");
                    return v;
                });

        stream.to(processedTopic, Produced.with(Serdes.String(), getSreamRequestSerde()));

        return stream;
    }

    private Serde<ChargingRequest> getSreamRequestSerde() {
        Map<String, Object> serdeProps = new HashMap<>();
        final Serializer<ChargingRequest> ChargingRequestSerializer = new JavaSerializer<>();
        serdeProps.put("JsonPOJOClass", ChargingRequest.class);
        ChargingRequestSerializer.configure(serdeProps, false);

        final Deserializer<ChargingRequest> ChargingRequestDeserializer = new JavaDeserializer<>();
        serdeProps.put("JsonPOJOClass", ChargingRequest.class);
        ChargingRequestDeserializer.configure(serdeProps, false);

        return Serdes.serdeFrom(ChargingRequestSerializer, ChargingRequestDeserializer);
    }
}
