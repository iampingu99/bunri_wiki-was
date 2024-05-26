package com.example.demo.bounded_context.recommendBoard.repository;

import com.example.demo.bounded_context.recommendBoard.entity.RecommendBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendBoardRepository extends JpaRepository<RecommendBoard, Long> {
    @Query(
            value = "SELECT id FROM RecommendBoard WHERE BOARD_ID = :boardId AND ACCOUNT_ID = :accountId  ",
                    nativeQuery = true
    )
    Long findByBoardAndAccount(@Param(value="boardId") Long boardId,
                                        @Param(value="accountId") Long accountId);
}
