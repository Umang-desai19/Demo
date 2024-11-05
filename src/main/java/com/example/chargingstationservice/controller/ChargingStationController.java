package com.example.chargingstationservice.controller;

import com.example.chargingstationservice.entity.ChargingRequest;
import com.example.chargingstationservice.entity.ChargingStation;
import com.example.chargingstationservice.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    @GetMapping("/getChargingStationInfo/{chargingStationId}")
    public ResponseEntity<ChargingStation> getChargingStationInfo(
            @PathVariable int chargingStationId) {

        return new ResponseEntity<>(chargingStationService.getChargingStationInfo(chargingStationId),
                HttpStatus.OK);
    }

    @PostMapping("/createChargingRequest")
    public ResponseEntity<ChargingRequest> createChargingRequest(
            @RequestBody ChargingRequest chargingRequest) {
        return new ResponseEntity<>(chargingStationService.createChargingRequest(chargingRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/getRequestStatus/{chargingRequestId}")
    public ResponseEntity<ChargingRequest> getRequestStatus(
            @PathVariable int chargingRequestId) {
        return new ResponseEntity<>(chargingStationService.getRequestStatus(chargingRequestId),
                HttpStatus.OK);
    }
}