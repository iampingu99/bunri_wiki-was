package com.example.demo.bounded_context.location.repository;

import com.example.demo.bounded_context.location.entity.LampAndBattery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LampAndBatteryRepository extends JpaRepository<LampAndBattery, Long> {
    @Query(value = "SELECT * FROM lamp_and_battery lb WHERE ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lb.longitude, lb.latitude)) <= :radius", nativeQuery = true)
    Page<LampAndBattery> findByCoordinate(Double latitude, Double longitude, Double radius, Pageable pageable);

    @Query(value = "SELECT lb FROM LampAndBattery lb WHERE lb.state = :state AND lb.city = :city")
    Page<LampAndBattery> findByAddress(String state, String city, Pageable pageable);
}
