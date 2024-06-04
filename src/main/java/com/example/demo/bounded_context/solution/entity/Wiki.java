package com.example.demo.bounded_context.solution.entity;

import com.example.demo.base.common.BaseTimeEntity;
import com.example.demo.bounded_context.account.entity.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wiki extends BaseTimeEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Account writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Waste waste;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wiki parent;

    private String name;
    private String solution;
    private String categories;
    private String tags;
    private WikiState wikiState;

    @Builder
    public Wiki(Account writer, Waste waste, String name, String solution, String categories, String tags, Wiki parent) {
        this.writer = writer;
        this.waste = waste;
        this.name = name;
        this.solution = solution;
        this.categories = categories;
        this.tags = tags;
        this.wikiState = WikiState.PENDING;
        this.parent = parent;
    }

    public void accept(){
        this.wikiState = WikiState.ACCEPTED;
    }
    public void reject() { this.wikiState = WikiState.REJECTED; }
}
