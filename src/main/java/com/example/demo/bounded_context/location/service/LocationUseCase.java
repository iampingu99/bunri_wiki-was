package com.example.demo.bounded_context.location.service;

import com.example.demo.bounded_context.location.dto.LocationResponse;
import com.example.demo.bounded_context.location.entity.LampAndBattery;
import com.example.demo.bounded_context.location.entity.Medicine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationUseCase {
    private final MedicineService medicineService;
    private final LampAndBatteryService lampAndBatteryService;

    public Page<LocationResponse> readMedicineListByAddress(String state, String city, Pageable pageable){
        Page<Medicine> medicineList = medicineService.findMedicineByAddress(state, city, pageable);
        return medicineList.map(m -> LocationResponse.of(m.getAddress(), m.getLatitude(), m.getLongitude()));
    }

    public Page<LocationResponse> readMedicineByCoordinate(Double latitude,
                                                           Double longitude,
                                                           Double radius,
                                                           Pageable pageable){
        Page<Medicine> medicineList = medicineService.findMedicineByCoordinate(latitude, longitude, radius, pageable);
        return medicineList.map(m -> LocationResponse.of(m.getAddress(), m.getLatitude(), m.getLongitude()));
    }

    public Page<LocationResponse> readLampAndBatteryListByAddress(String state, String city, Pageable pageable){
        Page<LampAndBattery> lampAndBatteryList = lampAndBatteryService.findLampAndBattery(state, city, pageable);
        return lampAndBatteryList.map(m -> LocationResponse.of(m.getAddress(), m.getLatitude(), m.getLongitude()));
    }

    public Page<LocationResponse> readLampAndBatteryListByCoordinate(Double latitude,
                                                                     Double longitude,
                                                                     Double radius,
                                                                     Pageable pageable){
        Page<LampAndBattery> lampAndBatteryList = lampAndBatteryService.findLampAndBattery(latitude, longitude, radius, pageable);
        return lampAndBatteryList.map(m -> LocationResponse.of(m.getAddress(), m.getLatitude(), m.getLongitude()));
    }
}
