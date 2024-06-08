package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.solution.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * 반영 된 솔루션 태그 이름으로 조회
     * full text search 도입 고민
     */
    @Query("select t.waste.id from Tag t where t.name = :name and t.waste.state = 1")
    Optional<Long> findIdByName(String name);
}
