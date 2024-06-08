package com.example.demo.bounded_context.solution.entity;

import com.example.demo.base.common.BaseTimeEntity;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Waste extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account writer;

    @NotNull @Column(unique = true)
    private String name;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "waste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "waste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @NotNull
    private String solution;

    @OneToMany(mappedBy = "waste")
    private Set<Wiki> wikis = new HashSet<>();

    private String imageUrl;

    private ContributedCreationState state;

    @Builder
    public Waste(@NotNull String name, @NotNull String solution, String imageUrl) {
        this.name = name;
        this.solution = solution;
        this.imageUrl = imageUrl;
        this.state = ContributedCreationState.PENDING;
    }

    public void setTags(String request){
        String[] split = request.split(",");
        for(String tagName: split){
            Tag tag = Tag.builder()
                    .name(tagName.trim())
                    .waste(this)
                    .build();
            this.tags.add(tag);
        }
    }

    public void setCategories(String request){
        String[] split = request.split(",");
        for(String categoryName: split){
            Category category = Category.builder()
                    .name(categoryName.trim())
                    .waste(this)
                    .build();
            this.categories.add(category);
        }
    }

    public void setWriter(Account writer){
        this.writer = writer;
        writer.getWaste().add(this);
    }

    public void update(String solution) {
        this.solution = solution;
    }

    public void accept(){
        this.state = ContributedCreationState.ACCEPTED;
    }

    public void reject(){
        this.state = ContributedCreationState.REJECTED;
    }
}
