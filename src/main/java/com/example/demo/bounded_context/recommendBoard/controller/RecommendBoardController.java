package com.example.demo.bounded_context.recommendBoard.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.questionBoard.dto.CreateQuestionBoardDto;
import com.example.demo.bounded_context.recommendBoard.repository.RecommendBoardRepository;
import com.example.demo.bounded_context.recommendBoard.service.RecommendBoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendBoard")
public class RecommendBoardController {
    private final RecommendBoardService recommendBoardService;
    private final RecommendBoardRepository recommendBoardRepository;

    @PostMapping("/{questionBoardId}") // - 추천
    @Operation(summary = "추천및 추천취소", description = "추천/이미 추천되어있을시 추천취소")
    public ResponseEntity<?> createQuestionBoard(@AuthorizationHeader Long accountId,
                                                 @PathVariable Long questionBoardId){
        Long recommendId = recommendBoardRepository.findByBoardAndAccount(questionBoardId, accountId);
        if(recommendId!=null)
        {
            recommendBoardService.delete(recommendId,questionBoardId);
            return ResponseEntity.ok("추천취소");
        }


        else{
            recommendBoardService.create(questionBoardId,accountId);
            return ResponseEntity.ok("추천완료");
        }
    }

}
