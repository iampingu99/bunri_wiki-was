package com.example.demo.bounded_context.recommendBoard.service;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.questionBoard.entity.QuestionBoard;
import com.example.demo.bounded_context.questionBoard.repository.QuestionBoardRepository;
import com.example.demo.bounded_context.recommendBoard.entity.RecommendBoard;
import com.example.demo.bounded_context.recommendBoard.repository.RecommendBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendBoardService {

    private final RecommendBoardRepository recommendBoardRepository;
    private final QuestionBoardRepository questionBoardRepository;
    private final AccountService accountService;

    //c
    @Transactional
    public void create(Long questionBoardId, Long accountId){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId)
                .orElseThrow(() -> new IllegalArgumentException("QUESTIONBOARD_NOT_FOUND"));
        Account account = accountService.read(accountId);

        RecommendBoard recommendBoard = RecommendBoard.builder()
                .account(account)
                .questionBoard(questionBoard)
                .build();

        questionBoard.recommend();
        recommendBoardRepository.save(recommendBoard);

    }

    @Transactional
    public void delete(Long recommendId, Long questionBoardId) {
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId)
                .orElseThrow(() -> new IllegalArgumentException("QUESTIONBOARD_NOT_FOUND"));

        RecommendBoard recommendBoard = recommendBoardRepository.findById(recommendId)
                .orElseThrow(() -> new IllegalArgumentException("RECOMMEND_NOT_FOUND"));

        questionBoard.unRecommend();
        recommendBoardRepository.delete(recommendBoard);
    }


}
