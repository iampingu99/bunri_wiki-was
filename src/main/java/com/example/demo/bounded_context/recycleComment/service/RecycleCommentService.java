package com.example.demo.bounded_context.recycleComment.service;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.recycleBoard.entity.RecycleBoard;
import com.example.demo.bounded_context.recycleBoard.repository.RecycleBoardRepository;
import com.example.demo.bounded_context.recycleComment.entity.RecycleComment;
import com.example.demo.bounded_context.recycleComment.repository.RecycleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecycleCommentService {

    private final RecycleCommentRepository recycleCommentRepository;
    private final RecycleBoardRepository recycleBoardRepository;

    //c
    @Transactional
    public void create(String content, Account writer, Long id){
        RecycleBoard recycleBoard = recycleBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QUESTIONBOARD_NOT_FOUND"));

        RecycleComment recycleComment = RecycleComment.builder()
                .content(content)
                .recycleBoard(recycleBoard)
                .writer(writer)
                .build();

        recycleCommentRepository.save(recycleComment);
    }

    //r
    @Transactional
    public RecycleComment read(Long recycleCommentId){
        return recycleCommentRepository.findById(recycleCommentId)
                .orElseThrow(() -> new IllegalArgumentException("RECYCLECOMMENT_NOT_FOUND"));
    }

    //u
    @Transactional
    public void update(Long recycleCommentId, String content){
        RecycleComment recycleComment = recycleCommentRepository.findById(recycleCommentId)
                .orElseThrow(() -> new IllegalArgumentException("RECYCLECOMMENT_NOT_FOUND"));

        recycleComment.update(content);
    }

    @Transactional
    public void delete(Long recycleCommentId){
        RecycleComment recycleComment = recycleCommentRepository.findById(recycleCommentId)
                .orElseThrow(() -> new IllegalArgumentException("RECYCLECOMMENT_NOT_FOUND"));

        recycleCommentRepository.delete(recycleComment);
    }

}
