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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailability() {
        return availability;
    }

    public int getChargingSpeed() {
        return chargingSpeed;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setChargingSpeed(int chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
