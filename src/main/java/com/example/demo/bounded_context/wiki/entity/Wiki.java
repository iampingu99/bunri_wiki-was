package com.example.demo.bounded_context.wiki.entity;

import com.example.demo.base.common.BaseTimeEntity;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.Waste;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Wiki original;

    @NotNull
    private String solution;

    @NotNull
    private String categories;

    @NotNull
    private String tags;

    private WikiState wikiState;

    @Builder
    public Wiki(Account writer, Waste waste, @NotNull String solution, @NotNull String categories, @NotNull String tags, Wiki original) {
        this.writer = writer;
        this.waste = waste;
        this.solution = solution;
        this.categories = categories;
        this.tags = tags;
        this.wikiState = WikiState.PENDING;
        this.original = original;
    }

    public void accept(){
        this.wikiState = WikiState.ACCEPTED;
    }
    public void reject() { this.wikiState = WikiState.REJECTED; }

    public void changeOriginal(Wiki original) { this.original = original; }
}
