package com.example.chargingstationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHARGING_STATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String location;
    private int capacity;
    private int availability;
    private int chargingSpeed;
    private String address;
}
