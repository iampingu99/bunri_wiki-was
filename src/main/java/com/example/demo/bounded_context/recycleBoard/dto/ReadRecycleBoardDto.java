package com.example.demo.bounded_context.recycleBoard.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.recycleBoard.entity.RecycleBoard;
import com.example.demo.bounded_context.recycleComment.dto.RecycleCommentResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadRecycleBoardDto {

    private String title;

    private String content;

    private Integer view;

    private String writer;

    private String location;

    private String shareTarget;

    private String imageUrl;

    private boolean collection;

    private List<RecycleCommentResponseDto> comments;

    private LocalDateTime createdDate;

    public ReadRecycleBoardDto(RecycleBoard recycleBoard){
        this.title= recycleBoard.getTitle();
        this.content=recycleBoard.getContent();
        this.location=recycleBoard.getLocation();
        this.shareTarget=recycleBoard.getShareTarget();
        this.writer=recycleBoard.getWriter().getNickname();
        this.collection=recycleBoard.isCollection();
        this.view=recycleBoard.getView();
        this.createdDate=recycleBoard.getCreatedDate();
        this.comments=recycleBoard.getComments().stream().map(RecycleCommentResponseDto::new).collect(Collectors.toList());
        this.imageUrl=recycleBoard.getImageUrl();
    }
}
