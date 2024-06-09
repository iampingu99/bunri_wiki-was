package com.example.demo.bounded_context.recommendComment.service;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import com.example.demo.bounded_context.questionBoard.repository.QuestionBoardRepository;
import com.example.demo.bounded_context.questionComment.entity.QuestionComment;
import com.example.demo.bounded_context.questionComment.repository.QuestionCommentRepository;
import com.example.demo.bounded_context.recommendBoard.entity.RecommendBoard;
import com.example.demo.bounded_context.recommendBoard.repository.RecommendBoardRepository;
import com.example.demo.bounded_context.recommendComment.entity.RecommendComment;
import com.example.demo.bounded_context.recommendComment.repository.RecommendCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendCommentService {

    private final RecommendCommentRepository recommendCommentRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final AccountService accountService;

    @Transactional
    public void create(Long questionCommentId, Long accountId){
        QuestionComment questionComment = questionCommentRepository.findById(questionCommentId)
                .orElseThrow(() -> new IllegalArgumentException("QUESTIONCOMMENT_NOT_FOUND"));
        Account account = accountService.findByAccountId(accountId);

        RecommendComment recommendComment = RecommendComment.builder()
                .account(account)
                .questionComment(questionComment)
                .build();

        questionComment.recommend();
        recommendCommentRepository.save(recommendComment);

    }

    @Transactional
    public void delete(Long recommendId, Long questionCommentId) {
        QuestionComment questionComment = questionCommentRepository.findById(questionCommentId)
                .orElseThrow(() -> new IllegalArgumentException("QUESITONCOMMENT_NOT_FOUND"));

        RecommendComment recommendComment = recommendCommentRepository.findById(recommendId)
                .orElseThrow(() -> new IllegalArgumentException("RECOMMEND_NOT_FOUND"));

        questionComment.unRecommend();
        recommendCommentRepository.delete(recommendComment);
    }

}
