package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {

    //@Query(value = "SELECT * from waste WHERE MATCH (name) AGAINST (:name IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    @Query("select w.id from Waste w where w.name = :name")
    Optional<Long> findIdByName(String name);

    /**
     * 사용자의 생성 기여 게시물 조회
     */
    @Query("select w from Waste w where w.writer.id = :accountId and w.state = :state")
    List<Waste> findByAccountIdAndState(Long accountId, ContributedCreationState state, Pageable pageable);

    /**
     * [x] 컬렉션 조인 문제 해결 (Set 사용은 임시)
     * [x] select wiki 분리
     */
    @Query("select w from Waste w join fetch w.categories left join fetch w.tags left join fetch w.wikis where w.id = :id")
    Waste findFetchById(Long id);

    @Query("select w from Waste w join fetch w.categories m group by w.id having count(m) = 1 and m.name = :name")
    List<Waste> findByCategoriesName(String name);
}
