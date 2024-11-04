package com.example.chargingstationservice.service;

import com.example.chargingstationservice.entity.ChargingRequest;
import com.example.chargingstationservice.entity.ChargingStation;
import com.example.chargingstationservice.repository.ChargingRequestRepository;
import com.example.chargingstationservice.repository.ChargingStationRepository;
import org.springframework.stereotype.Service;

@Service
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    private final ChargingRequestRepository chargingRequestRepository;

    public ChargingStationService(ChargingStationRepository chargingStationRepository,
            ChargingRequestRepository chargingRequestRepository) {
        this.chargingStationRepository = chargingStationRepository;
        this.chargingRequestRepository = chargingRequestRepository;
    }

    public ChargingStation getChargingStationInfo(int chargingStationId) {
        return  chargingStationRepository.findById(chargingStationId).get();
    }

    public ChargingRequest createChargingRequest(ChargingRequest chargingRequest) {
        return chargingRequestRepository.save(chargingRequest);
    }

    public ChargingRequest getRequestStatus(int chargingRequestId) {
        return chargingRequestRepository.findById(chargingRequestId).get();
    }
}
