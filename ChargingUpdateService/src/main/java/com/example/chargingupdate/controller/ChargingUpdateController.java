package com.example.chargingupdate.controller;

import com.example.chargingupdate.dto.ChargingRequest;
import com.example.chargingupdate.service.ChargingUpdateProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChargingUpdateController {

    @Autowired
    private ChargingUpdateProducerService chargingUpdateProducerService;

    @PostMapping("/publishUpdate")
    public ResponseEntity<HttpStatus> publishUpdate(@RequestBody ChargingRequest chargingRequest) {
        chargingUpdateProducerService.sendMessage(chargingRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}