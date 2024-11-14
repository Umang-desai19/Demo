package com.example.chargingstationservice.service;

import com.example.chargingstationservice.entity.ChargingRequest;
import com.example.chargingstationservice.entity.ChargingStation;
import com.example.chargingstationservice.repository.ChargingRequestRepository;
import com.example.chargingstationservice.repository.ChargingStationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChargingStationServiceTest {

    @Mock
    private ChargingStationRepository chargingStationRepository;

    @Mock
    private ChargingRequestRepository chargingRequestRepository;

    private ChargingStationService chargingStationService;

    @BeforeEach
    public void setUp() {
        chargingStationService = new ChargingStationService(
                chargingStationRepository, chargingRequestRepository);
    }

    @Test
    void testGetChargingStationInfo_valuePresent_verifyResponse() {
        Mockito.doReturn(Optional.of(new ChargingStation())).when(chargingStationRepository).findById(1);

        ChargingStation chargingStation = chargingStationService.getChargingStationInfo(1);
        Assertions.assertThat(chargingStation).isNotNull();
    }

    @Test
    void testGetChargingStationInfo_valueNotPresent_verifyNullResponse() {
        Mockito.doReturn(Optional.empty()).when(chargingStationRepository).findById(1);

        ChargingStation chargingStation = chargingStationService.getChargingStationInfo(1);
        Assertions.assertThat(chargingStation).isNull();
    }

    @Test
    void testGetRequestStatus_valuePresent_verifyResponse() {
        Mockito.doReturn(Optional.of(new ChargingRequest())).when(chargingRequestRepository).findById(1);

        ChargingRequest chargingRequest = chargingStationService.getRequestStatus(1);
        Assertions.assertThat(chargingRequest).isNotNull();
    }

    @Test
    void testGetRequestStatus_valueNotPresent_verifyNullResponse() {
        Mockito.doReturn(Optional.empty()).when(chargingRequestRepository).findById(1);

        ChargingRequest chargingRequest = chargingStationService.getRequestStatus(1);
        Assertions.assertThat(chargingRequest).isNull();
    }

    @Test
    void testCreateChargingRequest_verifyResponse() {
        ChargingRequest chargingRequest = new ChargingRequest();
        chargingRequest.setCarId(1);
        chargingRequest.setChargingStationId(1);
        chargingRequest.setDuration(60);
        chargingRequest.setRequestId(1);
        chargingRequest.setStatus("REQUEST_CREATED");

        Mockito.doReturn(chargingRequest).when(chargingRequestRepository)
                .save(chargingRequest);

        ChargingRequest response = chargingStationService.createChargingRequest(chargingRequest);
        assertNotNull(response);
        Assertions.assertThat(response).hasFieldOrPropertyWithValue("carId", 1)
                .hasFieldOrPropertyWithValue("chargingStationId", 1)
                .hasFieldOrPropertyWithValue("duration", 60)
                .hasFieldOrPropertyWithValue("requestId", 10)
                .hasFieldOrPropertyWithValue("status", "REQUEST_CREATED");

        Mockito.verify(chargingRequestRepository, Mockito.times(1))
                .save(chargingRequest);
    }
}