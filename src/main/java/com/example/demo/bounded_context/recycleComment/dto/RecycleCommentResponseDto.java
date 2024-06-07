package com.example.demo.bounded_context.recycleComment.dto;

import com.example.demo.bounded_context.recycleComment.entity.RecycleComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecycleCommentResponseDto {
    private Long id;
    private String content;
    private Long boardId;
    private String nickname;
    private String accountName;
    private LocalDateTime createdDate;

    public RecycleCommentResponseDto(RecycleComment recycleComment){
        this.id = recycleComment.getId();
        this.content = recycleComment.getContent();
        this.boardId = recycleComment.getRecycleBoard().getId();
        this.nickname = recycleComment.getWriter().getNickname();
        this.accountName = recycleComment.getWriter().getAccountName();
        this.createdDate = recycleComment.getCreatedDate();
    }

}
