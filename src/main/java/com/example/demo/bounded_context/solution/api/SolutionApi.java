package com.example.demo.bounded_context.solution.api;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.solution.dto.ContributeCreationListResponse;
import com.example.demo.bounded_context.solution.dto.ContributeCreationRequest;
import com.example.demo.bounded_context.solution.dto.ContributeCreationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Solution", description = "솔루션 관련 API")
public interface SolutionApi {
    @PostMapping("/api/solution")
    @Operation(summary = "새 솔루션 생성", description = "로그인한 사용자는 기존 솔루션에 없는 솔루션을 생성할 수 있다.<br> 이름, 카테고리, 태그, 새 솔루션을 생성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "새 솔루션 생성 성공"),
            @ApiResponse(responseCode = "409", description = "새 솔루션 생성 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "ACCOUNT_NOT_FOUND",
                                                                            "message": "해당 계정이름을 찾을 수 없습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> create(@AuthorizationHeader Long accountId,
                             @RequestBody ContributeCreationRequest request);

    @GetMapping("/api/solution")
    @Operation(summary = "새 솔루션 목록 페이지 조회", description = "사용자는 모든 새 솔루션을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모든 새 솔루션 목록 페이지 조회 성공")
    })
    ResponseEntity<?> readContributeCreationList(@PageableDefault(page = 0, size = 10) Pageable pageable);

    @GetMapping("/api/solution/{wasteId}")
    @Operation(summary = "솔루션 상세 조회", description = "사용자는 솔루션을 상세 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "솔루션 상세 조회 성공")
    })
    ResponseEntity<?> readContributeCreation(@PathVariable Long wasteId);

    @PutMapping("/api/solution/{wasteId}/accepted")
    @Operation(summary = "새 솔루션 승인", description = "관리자는 새 솔루션을 승인하여 검색 데이터로 사용할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "새 솔루션 승인 성공"),
            @ApiResponse(responseCode = "404", description = "새 솔루션 승인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "SOLUTION_NOT_FOUND",
                                                                            "message": "해당 솔루션이 존재하지 않습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> accept(@PathVariable Long wasteId);

    @PutMapping("/api/solution/{wasteId}/rejected")
    @Operation(summary = "새 솔루션 거부", description = "관리자는 새 솔루션을 거부할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "새 솔루션 거부 성공"),
            @ApiResponse(responseCode = "404", description = "새 솔루션 거부 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    										{
                                    										"timestamp": "2024-06-04T21:07:08.923528",
                                                                            "cause": "SOLUTION_NOT_FOUND",
                                                                            "message": "해당 솔루션이 존재하지 않습니다."
                                    										}
                                    """)
                    }))
    })
    ResponseEntity<?> reject(@PathVariable Long wasteId);
}
