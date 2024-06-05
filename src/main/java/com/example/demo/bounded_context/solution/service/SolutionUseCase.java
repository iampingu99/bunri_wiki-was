package com.example.demo.bounded_context.solution.service;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.solution.dto.ContributedCreationsResponse;
import com.example.demo.bounded_context.solution.dto.ContributedCreationRequest;
import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionUseCase {
    private final AccountService accountService;
    private final WasteService wasteService;

    public Long create(Long accountId, ContributedCreationRequest request){
        Account account = accountService.findByAccountId(accountId);
        Waste waste = wasteService.create(account, request.toEntity());
        return waste.getId();
    }

    public List<ContributedCreationsResponse> readCreatedContributions(Long accountId,
                                                                       ContributedCreationState state,
                                                                       Pageable pageable){
        List<Waste> createSolutionList = wasteService.findByAccountIdAndState(accountId, state, pageable);
        return createSolutionList.stream().map(ContributedCreationsResponse::fromEntity).toList();
    }
}
