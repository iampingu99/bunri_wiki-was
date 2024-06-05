package com.example.demo.bounded_context.account.controller;

import com.example.demo.base.Resolver.AccessToken;
import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.base.common.AuthConstants;
import com.example.demo.bounded_context.account.dto.AccountResponse;
import com.example.demo.bounded_context.account.dto.AccountUpdateRequest;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.account.service.AccountService;
import com.example.demo.bounded_context.solution.dto.ContributeCreationsResponse;
import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.service.SolutionUseCase;
import com.example.demo.bounded_context.wiki.dto.ContributeModificationsResponse;
import com.example.demo.bounded_context.wiki.entity.WikiState;
import com.example.demo.bounded_context.wiki.service.WikiUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final SolutionUseCase solutionUseCase;
    private final WikiUseCase wikiUseCase;

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "access / refresh token 을 사용한 로그아웃")
    public ResponseEntity<?> signOut(@AuthorizationHeader AccessToken accessToken,
                                  @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken) {
        accountService.signOut(accessToken, refreshToken);
        return ResponseEntity.ok().body("로그아웃에 성공했습니다.");
    }

    @DeleteMapping("/withdrawal")
    @Operation(summary = "회원 탈퇴", description = "access / refresh token 을 사용한 회원 탈퇴")
    public ResponseEntity<?> withdrawal(@AuthorizationHeader AccessToken accessToken,
                                         @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken) {
        accountService.withdrawal(accessToken, refreshToken);
        return ResponseEntity.ok().body("회원탈퇴에 성공했습니다.");
    }

    @GetMapping("/me")
    @Operation(summary = "정보 조회", description = "access token 을 사용한 사용자 정보 조회")
    public ResponseEntity<AccountResponse> read(@AuthorizationHeader Long id) {
        Account account = accountService.read(id);
        AccountResponse accountResponse = AccountResponse.fromEntity(account);
        return ResponseEntity.ok().body(accountResponse);
    }

    @PutMapping("/me")
    @Operation(summary = "정보 수정", description = "access token 을 사용한 사용자 정보 수정")
    public ResponseEntity<?> update(@AuthorizationHeader Long id, @RequestBody AccountUpdateRequest request) {
        accountService.update(id, request);
        return ResponseEntity.ok().body("수정에 성공했습니다.");
    }

    @GetMapping("/{accountId}/contributions/creation")
    private ResponseEntity<?> readContributeCreation(@PathVariable Long accountId,
                                                       @RequestParam(name = "state", defaultValue = "pending") String state,
                                                       @PageableDefault(page = 0, size = 10) Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        List<ContributeCreationsResponse> creations =
                solutionUseCase.readContributeCreations(accountId, ContributedCreationState.valueOf(state.toUpperCase()), pageable);
        return ResponseEntity.ok(creations);
    }

    @GetMapping("/{accountId}/contributions/modification")
    private ResponseEntity<?> readContributeModifications(@PathVariable Long accountId,
                                                       @RequestParam(name = "state", defaultValue = "pending") String state,
                                                       @PageableDefault(page = 0, size = 10) Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        List<ContributeModificationsResponse> modifications =
                wikiUseCase.readContributeModifications(accountId, WikiState.valueOf(state.toUpperCase()), pageable);
        return ResponseEntity.ok(modifications);
    }
}
