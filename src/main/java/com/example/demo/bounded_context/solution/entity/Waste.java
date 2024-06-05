package com.example.demo.bounded_context.solution.entity;

import com.example.demo.bounded_context.wiki.entity.Wiki;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "waste", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "waste", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags = new HashSet<>();

    private String solution;

    @OneToMany(mappedBy = "waste")
    private Set<Wiki> wikis = new HashSet<>();

    private String imageUrl;

    public void update(String solution) {
        this.solution = solution;
    }
}
