package com.example.demo.bounded_context.solution.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.solution.api.SolutionApi;
import com.example.demo.bounded_context.solution.dto.*;
import com.example.demo.bounded_context.solution.service.SolutionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SolutionController implements SolutionApi {
    private final SolutionUseCase solutionUseCase;

    public ResponseEntity<SolutionResponse> searchByKeyword(@RequestBody KeywordRequest request){
        SolutionResponse solution = solutionUseCase.searchByKeyword(request.keyword());
        return ResponseEntity.ok().body(solution);
    }

    public ResponseEntity<Page<SolutionListResponse>> searchByCategory(@RequestBody KeywordRequest request,
                                                                       @PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<SolutionListResponse> response = solutionUseCase.searchByCategory(request.keyword(), pageable);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Long> create(@AuthorizationHeader Long accountId,
                                    @RequestBody SolutionRequest request){
        Long id = solutionUseCase.create(accountId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    public ResponseEntity<Page<ContributeCreationListResponse>> readContributeCreationList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<ContributeCreationListResponse> creations = solutionUseCase.readContributeCreations(pageable);
        return ResponseEntity.ok(creations);
    }

    public ResponseEntity<SolutionResponse> readContributeCreation(@PathVariable Long wasteId){
        SolutionResponse creation = solutionUseCase.readContributeCreation(wasteId);
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
