package com.example.demo.bounded_context.solution.repository;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {

    public Boolean existsByWriterAndWasteAndIsAcceptFalse(Account writer, Waste waste);

    @Query("select w from Wiki w join fetch w.waste where w.id = :id")
    public Wiki findFetchById(Long id);
}
