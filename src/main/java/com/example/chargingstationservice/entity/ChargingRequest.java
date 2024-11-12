package com.example.chargingstationservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CHARGING_REQUEST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChargingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    private int carId;
    private int chargingStationId;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long remainingTime;
    private String BatteryStatus;
    private int percentComplete;
    private String status;
}
