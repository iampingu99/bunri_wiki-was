package com.example.demo.bounded_context.recommendComment.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.recommendBoard.repository.RecommendBoardRepository;
import com.example.demo.bounded_context.recommendBoard.service.RecommendBoardService;
import com.example.demo.bounded_context.recommendComment.repository.RecommendCommentRepository;
import com.example.demo.bounded_context.recommendComment.service.RecommendCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionComment/recommend")
public class RecommendCommentController {

    private final RecommendCommentService recommendCommentService;
    private final RecommendCommentRepository recommendCommentRepository;

    @PostMapping("/{questionCommentId}") // - 추천
    @Operation(summary = "추천및 추천취소", description = "추천/이미 추천되어있을시 추천취소")
    public ResponseEntity<?> recommend(@AuthorizationHeader Long accountId,
                                       @PathVariable Long questionCommentId){
        Long recommendId = recommendCommentRepository.findByCommentAndAccount(questionCommentId, accountId);
        if(recommendId!=null)
        {
            recommendCommentService.delete(recommendId,questionCommentId);
            return ResponseEntity.ok("추천취소");
        }


        else{
            recommendCommentService.create(questionCommentId,accountId);
            return ResponseEntity.ok("추천완료");
        }
    }

}
