package com.example.demo.bounded_context.solution.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.solution.api.SolutionApi;
import com.example.demo.bounded_context.solution.dto.*;
import com.example.demo.bounded_context.solution.service.SolutionService;
import com.example.demo.bounded_context.solution.service.SolutionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SolutionController implements SolutionApi {
    private final SolutionService solutionService;
    private final SolutionUseCase solutionUseCase;

    @GetMapping("/api/solution/keyword")
    @Operation(summary = "키워드 검색", description = "키워드를 사용한 솔루션 검색")
    private ResponseEntity<SolutionResponse> searchByKeyword(@RequestBody KeywordRequest request){
        SolutionResponse response = solutionService.search(request.keyword());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/solution/category")
    @Operation(summary = "카테고리별 필터링", description = "카테고리별 솔루션 검색")
    private ResponseEntity<List<WasteListResponse>> searchByMaterial(@RequestBody KeywordRequest request){
        List<WasteListResponse> response = solutionService.category(request.keyword());
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Long> create(@AuthorizationHeader Long accountId,
                                    @RequestBody ContributeCreationRequest request){
        Long id = solutionUseCase.create(accountId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    public ResponseEntity<List<ContributeCreationListResponse>> readContributeCreationList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        List<ContributeCreationListResponse> creations = solutionUseCase.readContributeCreations(pageable);
        return ResponseEntity.ok(creations);
    }

    public ResponseEntity<ContributeCreationResponse> readContributeCreation(@PathVariable Long wasteId){
        ContributeCreationResponse creation = solutionUseCase.readContributeCreation(wasteId);
        return ResponseEntity.ok(creation);
    }

    public ResponseEntity<Long> accept(@PathVariable Long wasteId){
        Long acceptSolutionId = solutionUseCase.accept(wasteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(acceptSolutionId);
    }

    public ResponseEntity<Long> reject(@PathVariable Long wasteId) {
        Long rejectSolutionId = solutionUseCase.reject(wasteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rejectSolutionId);
    }
}
