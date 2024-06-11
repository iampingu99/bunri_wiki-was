package com.example.demo.bounded_context.location.service;

import com.example.demo.bounded_context.location.entity.LampAndBattery;
import com.example.demo.bounded_context.location.repository.LampAndBatteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LampAndBatteryService {
    private final LampAndBatteryRepository lampAndBatteryRepository;

    public Page<LampAndBattery> findLampAndBattery (Double latitude, Double longitude, Double radius, Pageable pageable){
        return lampAndBatteryRepository.findByCoordinate(latitude, longitude, radius, pageable);
    }

    public Page<LampAndBattery> findLampAndBattery(String state, String city, Pageable pageable){
        return lampAndBatteryRepository.findByAddress(state, city, pageable);
    }
}
