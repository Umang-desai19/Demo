package com.example.chargingprocessor.service;

import com.example.chargingprocessor.entity.ChargingRequest;
import com.example.chargingprocessor.entity.ChargingStation;
import com.example.chargingprocessor.repository.ChargingRequestRepository;
import com.example.chargingprocessor.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class ChargingUpdateService {

    @Autowired
    private ChargingRequestRepository chargingRequestRepository;

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    public void UpdateChargingRequestDetails(ChargingRequest chargingRequest) {

        if (Optional.ofNullable(chargingRequest).isEmpty()) {
            return;
        }
        Optional<ChargingRequest> chargingRequestEntity = chargingRequestRepository.findById(
                chargingRequest.getRequestId());

        if (chargingRequestEntity.isEmpty()) {
            return;
        }
        chargingRequestEntity.get().setBatteryStatus(chargingRequest.getBatteryStatus());
        chargingRequestEntity.get().setPercentComplete(chargingRequest.getPercentComplete());
        chargingRequestEntity.get().setStatus(chargingRequest.getStatus());

        if (Optional.ofNullable(chargingRequest.getStartTime()).isPresent()) {
            chargingRequestEntity.get().setStartTime(chargingRequest.getStartTime());
        }
        if (Optional.ofNullable(chargingRequest.getEndTime()).isPresent()) {
            chargingRequestEntity.get().setEndTime(chargingRequest.getEndTime());
        }
        if (Optional.ofNullable(chargingRequestEntity.get().getEndTime()).isPresent()) {
            chargingRequestEntity.get().setRemainingTime(LocalDateTime.now().until(
                    chargingRequestEntity.get().getEndTime(), ChronoUnit.MINUTES));
        }
        chargingRequestRepository.save(chargingRequestEntity.get());

        int difference = 0;
        if (chargingRequest.getStatus().equalsIgnoreCase("CHARGING_STARTED")) {
            difference = -1;
        } else if (chargingRequest.getStatus().equalsIgnoreCase("CHARGING_COMPLETED")) {
            difference = 1;
        }
        if (difference != 0) {
            updateChargingStationAvailability(chargingRequest.getChargingStationId(), difference);
        }
    }

    private void updateChargingStationAvailability(int stationId, int difference) {
        Optional<ChargingStation> chargingStationEntity = chargingStationRepository.findById(stationId);

        if (chargingStationEntity.isEmpty()) {
            return;
        }
        chargingStationEntity.get().setAvailability(
                chargingStationEntity.get().getAvailability() + difference);
        chargingStationRepository.save(chargingStationEntity.get());
    }
}
