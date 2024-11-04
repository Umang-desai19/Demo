package com.example.chargingupdate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargingRequest implements Serializable {
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
