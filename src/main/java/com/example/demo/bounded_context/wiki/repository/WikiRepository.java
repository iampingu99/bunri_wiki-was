package com.example.demo.bounded_context.wiki.repository;

import com.example.demo.bounded_context.solution.entity.Waste;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
    @Query("select w from Wiki w join fetch w.writer a where a.id = :writerId and w.waste.id = :wasteId and w.wikiState = 0")
    public Optional<Wiki> existsPending(Long writerId, Long wasteId);

    @Query("select w from Wiki w join fetch w.waste t where t.id = :wasteId and w.wikiState = 1 order by w.modifiedDate desc")
    public Optional<Wiki> findByRecent(Long wasteId);

    @Query("select w from Wiki w join fetch w.writer join fetch w.waste where w.id = :id")
    public Optional<Wiki> findFetchById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Wiki w SET w.original = :original WHERE w.waste = :waste")
    public void updateOrigin(Wiki original, Waste waste);
}
