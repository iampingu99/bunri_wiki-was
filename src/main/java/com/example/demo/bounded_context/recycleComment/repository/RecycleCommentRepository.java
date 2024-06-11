package com.example.demo.bounded_context.recycleComment.repository;

import com.example.demo.bounded_context.recycleComment.entity.RecycleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecycleCommentRepository extends JpaRepository<RecycleComment, Long> {

}
