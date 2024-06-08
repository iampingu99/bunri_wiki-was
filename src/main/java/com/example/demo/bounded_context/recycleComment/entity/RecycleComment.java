package com.example.demo.bounded_context.recycleComment.entity;

import com.example.demo.base.common.BaseTimeEntity;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import com.example.demo.bounded_context.recycleBoard.entity.RecycleBoard;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RecycleComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "RECYCLE_ID", nullable = false)
    @ManyToOne
    private RecycleBoard recycleBoard;

    @JoinColumn(name = "COMMENT_WRITER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Account writer;

    @Builder
    public RecycleComment(String content, Integer recommend, RecycleBoard recycleBoard, Account writer){
        this.content=content;
        this.recycleBoard=recycleBoard;
        this.writer=writer;
    }

    public void update(String content) {
        this.content = content;
    }

}
