package com.example.chargingprocessor.repository;

import com.example.chargingprocessor.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,  Integer> {

}
