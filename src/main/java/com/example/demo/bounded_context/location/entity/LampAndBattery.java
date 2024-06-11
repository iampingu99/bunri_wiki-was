package com.example.demo.bounded_context.location.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class LampAndBattery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String address;
    String state;
    String city;
    String subCity;
    String subAddress;
    Double latitude;
    Double longitude;
}
