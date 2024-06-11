package com.example.demo.bounded_context.location.service;

import com.example.demo.bounded_context.location.entity.Medicine;
import com.example.demo.bounded_context.location.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public Page<Medicine> findMedicineByAddress (String state, String city, Pageable pageable){
        return medicineRepository.findByAddress(state, city, pageable);
    }

    public Page<Medicine> findMedicineByCoordinate (Double latitude, Double longitude, Double radius, Pageable pageable){
        return medicineRepository.findByCoordinate(latitude, longitude, radius, pageable);
    }
}
