package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.solution.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {

    @Query("select w.id from Waste w where w.name = :name")
    Optional<Long> findIdByName(String name);

    /**
     * 태그와 위키는 존재하지 않을 수 있으므로 left join
     */
    @Query("select w from Waste w join fetch w.categories left join fetch w.tags left join fetch w.wikis where w.id = :id")
    Waste findFetchById(Long id);

    @Query("select w from Waste w join fetch w.categories m group by w.id having count(m) = 1 and m.name = :name")
    List<Waste> findByCategoriesName(String name);
}
