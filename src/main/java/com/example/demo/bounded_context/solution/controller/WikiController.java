package com.example.demo.bounded_context.solution.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.solution.dto.WikiCompareResponse;
import com.example.demo.bounded_context.solution.dto.WikiRequest;
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

    /**
     * [o] 반영중인 위키 검사
     * [o] 부모 위키 연결
     * [x] 쿼리 최적화
     */
    @PostMapping("/api/solution/{wasteId}/wiki")
    @Operation(summary = "위키 작성", description = "기존 데이터에 대한 위키 작성")
    private ResponseEntity<Long> create(@AuthorizationHeader Long writerId,
                                        @PathVariable("wasteId") Long wasteId,
                                        @RequestBody WikiRequest request){
        Wiki wiki = wikiService.create(accountService.findByAccountId(writerId), wasteService.findById(wasteId), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(wiki.getId());
    }

    /**
     * [o] 부모 위키와 정보 비교
     * [x] 쿼리 최적화
     */
    @GetMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 정보 조회", description = "위키 정보 조회")
    private ResponseEntity<?> read(@PathVariable("wikiId") Long wikiId){
        WikiCompareResponse response = wikiService.read(wikiId);
        return ResponseEntity.ok().body(response);
    }

    /**
     * [x] 로그인 filter
     * [o] 위키 작성자 검사
     * [o] 위키 상태 검사
     */
    @DeleteMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 정보 삭제", description = "위키 정보 삭제")
    private ResponseEntity<?> delete(@AuthorizationHeader Long userId,
                                     @PathVariable("wikiId") Long wikiId){
        wikiService.delete(userId, wikiId);
        return ResponseEntity.ok().body("위키가 삭제되었습니다.");
    }


    /**
     * [x] 관리자 권한 검사
     * [o] 기존 데이터 대체
     * [x] 쿼리 최적화
     */
    @PutMapping("/api/wiki/{wikiId}/accepted")
    @Operation(summary = "위키 정보 승인", description = "위키 정보를 반영하여 기존 데이터 대체")
    private ResponseEntity<?> accept(@PathVariable("wikiId") Long wikiId){
        wikiService.accept(wikiId);
        return ResponseEntity.ok().body("위키 요청 승인이 완료되었습니다.");
    }

    /**
     * [x] 관리자 권한 검사
     */
    @PutMapping("/api/wiki/{wikiId}/rejected")
    @Operation(summary = "위키 정보 거부", description = "위키 정보를 거절")
    private ResponseEntity<?> reject(@PathVariable("wikiId") Long wikiId){
        wikiService.reject(wikiId);
        return ResponseEntity.ok().body("위키 요청 거부가 완료되었습니다.");
    }
}
