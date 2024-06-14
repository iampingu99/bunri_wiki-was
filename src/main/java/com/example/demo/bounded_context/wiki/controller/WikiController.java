package com.example.demo.bounded_context.wiki.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.wiki.api.WikiApi;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.wiki.dto.WikiCompareResponse;
import com.example.demo.bounded_context.wiki.dto.WikiListResponse;
import com.example.demo.bounded_context.wiki.dto.WikiRequest;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.service.WikiService;
import com.example.demo.bounded_context.solution.service.WasteService;
import com.example.demo.bounded_context.wiki.service.WikiUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WikiController implements WikiApi {

    private final WasteService wasteService;
    private final WikiService wikiService;
    private final AccountService accountService;
    private final WikiUseCase wikiUseCase;

    /**
     * [o] 반영중인 위키 검사
     * [o] 부모 위키 연결
     * [x] 쿼리 최적화
     */
    public ResponseEntity<Long> create(@AuthorizationHeader Long accountId,
                                       @PathVariable("wasteId") Long wasteId,
                                       @RequestBody WikiRequest request){
        Wiki wiki = wikiService.create(accountService.findByAccountId(accountId), wasteService.findById(wasteId), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(wiki.getId());
    }

    /**
     * [o] 부모 위키와 정보 비교
     * [x] 쿼리 최적화
     */
    public ResponseEntity<WikiCompareResponse> read(@PathVariable("wikiId") Long wikiId){
        WikiCompareResponse response = wikiService.read(wikiId);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Page<WikiListResponse>> readByWasteId(@PathVariable("wasteId") Long wasteId,
                                                          @PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<WikiListResponse> response = wikiUseCase.readByWasteId(wasteId, pageable);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Page<WikiListResponse>> readAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<WikiListResponse> response = wikiUseCase.readAll(pageable);
        return ResponseEntity.ok().body(response);
    }

    /**
     * [x] 로그인 filter
     * [o] 위키 작성자 검사
     * [o] 위키 상태 검사
     */
    public ResponseEntity<?> delete(@AuthorizationHeader Long userId,
                                     @PathVariable("wikiId") Long wikiId){
        wikiService.delete(userId, wikiId);
        return ResponseEntity.ok().body("위키가 삭제되었습니다.");
    }


    /**
     * [x] 관리자 권한 검사
     * [o] 기존 데이터 대체
     * [x] 쿼리 최적화
     */
    public ResponseEntity<?> accept(@PathVariable("wikiId") Long wikiId){
        wikiService.accept(wikiId);
        return ResponseEntity.ok().body("위키 요청 승인이 완료되었습니다.");
    }

    /**
     * [x] 관리자 권한 검사
     */
    public ResponseEntity<?> reject(@PathVariable("wikiId") Long wikiId){
        wikiService.reject(wikiId);
        return ResponseEntity.ok().body("위키 요청 거부가 완료되었습니다.");
    }

    //readAll : 배출방법의 모든 위키
    //read wiki : 사용자의 위키 정보
    //read new wiki : 사용자의 새로운 위키 정보
}
