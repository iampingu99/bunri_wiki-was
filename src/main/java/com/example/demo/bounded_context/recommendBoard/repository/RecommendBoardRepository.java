package com.example.demo.bounded_context.recommendBoard.repository;

import com.example.demo.bounded_context.recommendBoard.entity.RecommendBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendBoardRepository extends JpaRepository<RecommendBoard, Long> {
    @Query(
            value = "SELECT id FROM recommend_board WHERE board_id = :boardId AND account_id = :accountId  ",
                    nativeQuery = true
    )
    Long findByBoardAndAccount(@Param(value="boardId") Long boardId,
                                        @Param(value="accountId") Long accountId);
}
