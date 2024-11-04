package com.example.chargingprocessor.repository;

import com.example.chargingprocessor.entity.ChargingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingRequestRepository extends JpaRepository<ChargingRequest,  Integer> {

}
