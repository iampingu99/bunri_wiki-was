package com.example.demo.bounded_context.recommendBoard.entity;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RecommendBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private QuestionBoard questionBoard;


    @Builder
    public RecommendBoard(Account account, QuestionBoard questionBoard){
        this.account=account;
        this.questionBoard=questionBoard;
    }

}
