package com.example.demo.bounded_context.location.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.location.dto.LocationResponse;
import com.example.demo.bounded_context.location.service.LampAndBatteryService;
import com.example.demo.bounded_context.location.service.LocationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
@Tag(name = "Location", description = "수거함 위치 관련 API")
public class LocationController {
     final LampAndBatteryService lampAndBatteryService;
     final AccountService accountService;
     final LocationUseCase locationUseCase;

    @GetMapping("/medicine")
    @Operation(summary = "주소를 사용한 폐의약품 위치 목록 조회", description = "모든 사용자는 주소를 사용한 폐의약품 수거함 위치를 조회할 수 있다.")
    public ResponseEntity<Page<LocationResponse>> findMedicine(@Parameter(description = "도/시", example = "대구")@RequestParam("state") String state,
                                                               @Parameter(description = "구/군", example = "달서구")@RequestParam("city") String city,
                                                               @Parameter(example = "page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<LocationResponse> response = locationUseCase.readMedicineListByAddress(state, city, pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/medicine/coordinate")
    @Operation(summary = "좌표를 사용한 폐의약품 위치 목록 조회", description = "로그인한 사용자는 좌표및 반경을 사용한 폐의약품 수거함 위치를 조회할 수 있다.")
    public ResponseEntity<Page<LocationResponse>> findMedicine(@AuthorizationHeader Long id,
                                                               @Parameter(description = "위도", example = "35.84061406")@RequestParam(value = "latitude") Double latitude,
                                                               @Parameter(description = "경도", example = "128.5531486")@RequestParam(value = "longitude") Double longitude,
                                                               @Parameter(description = "반경(m)", example = "1000")@RequestParam(value = "radius", defaultValue = "1000") Double radius,
                                                               @Parameter(example = "page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<LocationResponse> response = locationUseCase.readMedicineByCoordinate(latitude, longitude, radius, pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/lampAndBattery")
    @Operation(summary = "주소를 사용한 폐형광등 및 폐건전지 위치 목록 조회", description = "모든 사용자는 주소를 사용한 폐형광등 및 폐건전지 수거함 위치를 조회할 수 있다.")
    public ResponseEntity<Page<LocationResponse>> findLampAndBattery(@Parameter(description = "도/시", example = "대구")@RequestParam("state") String state,
                                                                     @Parameter(description = "구/군", example = "달서구")@RequestParam("city") String city,
                                                                     @Parameter(example = "page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<LocationResponse> response = locationUseCase.readLampAndBatteryListByAddress(state, city, pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/lampAndBattery/coordinate")
    @Operation(summary = "좌표를 사용한 폐형광등 및 폐건전지 위치 목록 조회", description = "로그인한 사용자는 좌표및 반경을 사용한 폐형광등 및 폐건전지 수거함 위치를 조회할 수 있다.")
    public ResponseEntity<Page<LocationResponse>> findLampAndBattery(@AuthorizationHeader Long id,
                                                                     @Parameter(description = "위도", example = "35.84061406")@RequestParam(value = "latitude") Double latitude,
                                                                     @Parameter(description = "경도", example = "128.5531486")@RequestParam(value = "longitude") Double longitude,
                                                                     @Parameter(description = "반경(m)", example = "1000")@RequestParam(value = "radius", defaultValue = "1000") Double radius,
                                                                     @Parameter(example = "page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<LocationResponse> response = locationUseCase.readLampAndBatteryListByCoordinate(latitude, longitude, radius, pageable);
        return ResponseEntity.ok().body(response);
    }
}

