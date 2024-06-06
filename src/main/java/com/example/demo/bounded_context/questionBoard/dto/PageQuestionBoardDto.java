package com.example.demo.bounded_context.questionBoard.dto;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageQuestionBoardDto {
    private Long id;

    private String title;

    private Integer recommend;

    private String writer;

    private boolean adopted;

    private Integer view;

    private LocalDateTime createdDate;
    public PageQuestionBoardDto(QuestionBoard questionBoard) {
        this.id=questionBoard.getId();
        this.title = questionBoard.getTitle();
        this.recommend = questionBoard.getRecommend();
        this.writer = questionBoard.getWriter().getNickname();
        this.adopted=questionBoard.isAdopted();
        this.view = questionBoard.getView();
        this.createdDate = questionBoard.getCreatedDate();
    }
}
