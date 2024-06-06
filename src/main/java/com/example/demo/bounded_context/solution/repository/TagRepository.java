package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * 반영 된 솔루션 태그 이름으로 조회
     * full text search 도입 고민
     */
    @Query("select w.id from Tag t join fetch t.waste w where t.name = :name and w.state = 1")
    Optional<Long> findIdByName(String name);
}
