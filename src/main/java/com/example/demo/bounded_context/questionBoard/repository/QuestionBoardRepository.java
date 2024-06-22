package com.example.demo.bounded_context.questionBoard.repository;

import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {

    Page<QuestionBoard> findByTitleContaining(String title, Pageable pageable);

}
