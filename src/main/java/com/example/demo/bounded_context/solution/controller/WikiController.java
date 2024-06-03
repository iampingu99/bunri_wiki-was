package com.example.demo.bounded_context.solution.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.solution.dto.WikiRequest;
import com.example.demo.bounded_context.solution.dto.WikiResponse;
import com.example.demo.bounded_context.solution.entity.Wiki;
import com.example.demo.bounded_context.solution.service.WikiService;
import com.example.demo.bounded_context.solution.service.WasteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WikiController {

    private final WasteService wasteService;
    private final WikiService wikiService;
    private final AccountService accountService;

    @PostMapping("/api/solution/{wasteId}/wiki")
    @Operation(summary = "위키 작성", description = "기존 데이터에 대한 위키 작성")
    private ResponseEntity<Long> create(@AuthorizationHeader Long writerId,
                                        @PathVariable("wasteId") Long wasteId,
                                        @RequestBody WikiRequest request){
        Wiki wiki = wikiService.create(accountService.findByAccountId(writerId), wasteService.findById(wasteId), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(wiki.getId());
    }

    @GetMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 정보 조회", description = "위키 정보 조회")
    private ResponseEntity<?> read(@PathVariable("wikiId") Long opinionId){
        Wiki wiki = wikiService.read(opinionId);
        WikiResponse response = WikiResponse.fromEntity(wiki);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 반영", description = "위키를 반영하여 기존 데이터 대체")
    private ResponseEntity<?> accept(@PathVariable("wikiId") Long wikiId){
        wikiService.accept(wikiId);
        return ResponseEntity.ok().body("success ");
    }
}
