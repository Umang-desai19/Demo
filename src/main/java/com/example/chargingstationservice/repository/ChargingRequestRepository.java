package com.example.chargingstationservice.repository;

import com.example.chargingstationservice.entity.ChargingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingRequestRepository extends JpaRepository<ChargingRequest,  Integer> {

}
