package com.example.chargingstationservice.repository;

import com.example.chargingstationservice.entity.ChargingRequest;
import com.example.chargingstationservice.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,  Integer> {

}
