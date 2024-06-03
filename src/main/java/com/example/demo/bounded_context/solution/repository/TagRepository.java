package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("select t.waste.id from Tag t where t.name = :name")
    Optional<Long> findIdByName(String name);
}
