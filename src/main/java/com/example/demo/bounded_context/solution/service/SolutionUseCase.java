package com.example.demo.bounded_context.solution.service;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.solution.dto.ContributeCreationResponse;
import com.example.demo.bounded_context.solution.dto.ContributeCreationListResponse;
import com.example.demo.bounded_context.solution.dto.ContributeCreationRequest;
import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionUseCase {
    private final AccountService accountService;
    private final WasteService wasteService;

    /**
     * 생성 기여 게시물 생성
     * - 사용자는 생성 기여 게시물을 생성 할 수 있다.
     */
    public Long create(Long accountId, ContributeCreationRequest request){
        Account account = accountService.findByAccountId(accountId);
        Waste waste = wasteService.create(account, request.toEntity());
        return waste.getId();
    }

    /**
     * 사용자의 생성 기여 게시물 목록 조회
     * - 사용자는 자신을 포함한 다른 사용자의 생성 기여 게시물 목록을 조회할 수 있다.
     */
    public List<ContributeCreationListResponse> readContributeCreations(Long accountId,
                                                                        ContributedCreationState state,
                                                                        Pageable pageable){
        Page<Waste> accountCreations = wasteService.findByAccountIdAndStateWithPaging(accountId, state, pageable);
        return accountCreations.stream().map(ContributeCreationListResponse::fromEntity).toList();
    }

    /**
     * 모든 생성 기여 게시물 목록 조회
     * - 사용자는 모든 생성 기여 목록을 조회할 수 있다.
     */
    public List<ContributeCreationListResponse> readContributeCreations(Pageable pageable){
        Page<Waste> allCreations = wasteService.findAllFetchWithPaging(pageable);
        return allCreations.stream().map(ContributeCreationListResponse::fromEntity).toList();
    }

    /**
     * 생성 기여 게시물 상세 조회
     * - 사용자는 생성 기여 게시물을 상세 조회할 수 있다.
     */
    public ContributeCreationResponse readContributeCreation(Long wastId){
        Waste creation = wasteService.findFetchById(wastId);
        return ContributeCreationResponse.fromEntity(creation);
    }
}
