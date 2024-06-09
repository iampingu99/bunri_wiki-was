package com.example.demo.bounded_context.recommendComment.entity;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.questionComment.entity.QuestionComment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RecommendComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private QuestionComment questionComment;


    @Builder
    public RecommendComment(Account account, QuestionComment questionComment){
        this.account=account;
        this.questionComment=questionComment;
    }

}
