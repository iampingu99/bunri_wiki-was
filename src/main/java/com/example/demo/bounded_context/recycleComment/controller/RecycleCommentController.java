package com.example.demo.bounded_context.recycleComment.controller;

import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.questionComment.entity.QuestionComment;
import com.example.demo.bounded_context.recycleComment.entity.RecycleComment;
import com.example.demo.bounded_context.recycleComment.service.RecycleCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recycleComment")
public class RecycleCommentController {

    private final RecycleCommentService recycleCommentService;
    private final AccountService accountService;

    @PostMapping("/{recycleBoardId}/create") // - 로그인한 사용자는 댓글을 작성할 수 있다
    @Operation(summary = "댓글 작성", description = "로그인시 나눔 게시글에 댓글 작성 가능")
    public ResponseEntity<?> create(@PathVariable Long recycleBoardId,
                                    @AuthorizationHeader Long id,
                                    @RequestBody String content){
        Account writer = accountService.findByAccountId(id);; // writer가 현재 누구
        recycleCommentService.create(content,writer,recycleBoardId);

        return ResponseEntity.ok("댓글 생성완료");
    }

    @PutMapping("/update/{recycleCommentId}") // - 댓글 업데이트
    @Operation(summary = "댓글 수정", description = "자신이 작성한 댓글 수정")
    public ResponseEntity<?> update(@AuthorizationHeader Long id,
                                    @PathVariable Long recycleCommentId,
                                    @RequestBody String content) {
        Account user = accountService.findByAccountId(id);
        RecycleComment recycleComment=recycleCommentService.read(recycleCommentId);
        if(recycleComment.getWriter()==user){
            recycleCommentService.update(recycleCommentId,content);
            return ResponseEntity.ok("댓글 수정완료");
        }
        else{
            return ResponseEntity.ok("권한이 없습니다");
        }
    }

    @GetMapping("/delete/{recycleCommentId}")  // - 댓글 삭제
    @Operation(summary = "댓글 삭제", description = "자신이 작성한 댓글 삭제")
    public ResponseEntity<?> delete(@AuthorizationHeader Long id,
                                    @PathVariable Long recycleCommentId) {
        Account user = accountService.findByAccountId(id);
        RecycleComment recycleComment=recycleCommentService.read(recycleCommentId);
        if(recycleComment.getWriter()==user){
            recycleCommentService.delete(recycleCommentId);
            return ResponseEntity.ok("댓글 삭제완료");
        }
        else{
            return ResponseEntity.ok("권한이 없습니다");
        }
    }


}
