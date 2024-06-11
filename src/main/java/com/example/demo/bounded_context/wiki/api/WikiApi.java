package com.example.demo.bounded_context.wiki.api;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.wiki.dto.WikiListResponse;
import com.example.demo.bounded_context.wiki.dto.WikiRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Wiki", description = "위키 관련 API")
public interface WikiApi {
    @PostMapping("/api/solution/{wasteId}/wiki")
    @Operation(summary = "위키 요청 생성", description = "로그인한 사용자는 기존 배출정보에 대한 위키 요청을 생성할 수 있다.<br> 카테고리, 태그, 솔루션에 대한 위키 요청을 생성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "위키 생성 성공"),
            @ApiResponse(responseCode = "409", description = "위키 생성 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "EXIST_WIKI",
                                                                            "message": "해당 배출정보의 보류 상태인 사용자의 위키가 존재합니다."
                                    										}
                                    """)
                    })),
            @ApiResponse(responseCode = "500", description = "위키 생성 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "ConstraintViolationException",
                                                                            "message": "빈 필드 값이 존재합니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> create(@AuthorizationHeader Long accountId,
                                @PathVariable("wasteId") Long wasteId,
                                @RequestBody WikiRequest request);

    @GetMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 정보 조회", description = "모든 사용자는 위키 정보를 조회하고 이전 버전과 비교할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "위키 조회 성공"),
            @ApiResponse(responseCode = "404", description = "위키 조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "EMPTY_WIKI",
                                                                            "message": "해당 위키가 존재하지 않습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> read(@PathVariable("wikiId") Long wikiId);

    @GetMapping("/api/solution/{wasteId}/wiki")
    @Operation(summary = "솔루션의 위키 목록 조회", description = "모든 사용자는 해당 솔루션의 위키 목록을 조회할 수 있다.")
    ResponseEntity<Page<WikiListResponse>> readAll(@PathVariable("wasteId") Long wasteId,
                                                   @PageableDefault(page = 0, size = 10) Pageable pageable);

    @DeleteMapping("/api/wiki/{wikiId}")
    @Operation(summary = "위키 정보 삭제", description = "로그인한 사용자 중 위키의 작성자는 위키 요청이 수락 또는 거부되기 전에 생성한 위키 요청를 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "위키 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "위키 삭제 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "EMPTY_WIKI",
                                                                            "message": "해당 위키가 존재하지 않습니다."
                                    										}
                                    """)
                    })),
            @ApiResponse(responseCode = "409", description = "위키 삭제 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "NOT_PENDING_WIKI",
                                                                            "message": "반영 및 거부된 위키는 수정 또는 삭제가 불가능합니다."
                                    										}
                                    """)
                    })),
    })
    ResponseEntity<?> delete(@AuthorizationHeader Long userId,
                             @PathVariable("wikiId") Long wikiId);

    @PutMapping("/api/wiki/{wikiId}/accepted")
    @Operation(summary = "위키 요청 승인", description = "관리자는 사용자의 위키 요청을 반영하여 기존 정보를 대체할 수 있다.<br>승인된 위키는 보류중인 위키들의 원본이 된다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "위키 요청 승인 성공"),
            @ApiResponse(responseCode = "404", description = "위키 요청 승인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "EMPTY_WIKI",
                                                                            "message": "해당 위키가 존재하지 않습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> accept(@PathVariable("wikiId") Long wikiId);

    @PutMapping("/api/wiki/{wikiId}/rejected")
    @Operation(summary = "위키 요청 거부", description = "관리자는 사용자의 위키 요청을 거부할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "위키 요청 거부 성공"),
            @ApiResponse(responseCode = "404", description = "위키 요청 승인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "EMPTY_WIKI",
                                                                            "message": "해당 위키가 존재하지 않습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> reject(@PathVariable("wikiId") Long wikiId);
}
