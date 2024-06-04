package com.example.demo.bounded_context.solution.service;

import com.example.demo.base.exception.CustomException;
import com.example.demo.base.exception.ExceptionCode;
import com.example.demo.bounded_context.solution.dto.SolutionResponse;
import com.example.demo.bounded_context.solution.dto.WasteListResponse;
import com.example.demo.bounded_context.solution.entity.Waste;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SolutionService {
    private final WasteService wasteService;
    private final TagService tagService;

    public Long isExist(String name){
        log.info("키워드 검색");
        return wasteService.findByName(name)
                .or(() -> tagService.findIdByName(name))
                .orElseThrow(() -> new CustomException(ExceptionCode.WASTE_NOT_FOUND));
    }

    public SolutionResponse search(String wasteName){
        Long wasteId = isExist(wasteName);
        log.info("폐기물 정보 출력");
        Waste waste = wasteService.findFetchById(wasteId);
        return SolutionResponse.fromEntity(waste);
    }

    public List<WasteListResponse> category(String categoryName){
        log.info("폐기물 목록 출력");
        List<Waste> wasteList = wasteService.findFetchByCategory(categoryName);
        return wasteList.stream()
                .map(WasteListResponse::fromEntity).toList();
    }
}
