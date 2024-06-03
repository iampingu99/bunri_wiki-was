package com.example.demo.bounded_context.solution.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Waste waste;

    @Column(unique = true)
    private String name;

    @Builder
    public Tag(Waste waste, String name) {
        this.waste = waste;
        this.name = name;
    }
}
