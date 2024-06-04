package com.example.demo.bounded_context.questionBoard.dto;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import lombok.*;

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
    public PageQuestionBoardDto(QuestionBoard questionBoard) {
        this.id=questionBoard.getId();
        this.title = questionBoard.getTitle();
        this.recommend = questionBoard.getRecommend();
        this.writer = questionBoard.getWriter().getNickname();
        this.adopted=questionBoard.isAdopted();
        this.view = questionBoard.getView();
    }
}
