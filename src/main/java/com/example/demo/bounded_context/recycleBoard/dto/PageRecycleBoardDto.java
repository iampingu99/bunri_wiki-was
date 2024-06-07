package com.example.demo.bounded_context.recycleBoard.dto;

import com.example.demo.bounded_context.recycleBoard.entity.RecycleBoard;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRecycleBoardDto {

    private Long id;

    private String title;

    private String writer;

    private boolean collection;

    private Integer view;

    private LocalDateTime createdDate;

    public PageRecycleBoardDto(RecycleBoard recycleBoard){
        this.id= recycleBoard.getId();
        this.title= recycleBoard.getTitle();
        this.writer=recycleBoard.getWriter().getNickname();
        this.collection= recycleBoard.isCollection();
        this.view= recycleBoard.getView();
        this.createdDate=recycleBoard.getCreatedDate();
    }

}
