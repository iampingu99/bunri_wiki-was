package com.example.demo.bounded_context.account.controller;

import com.example.demo.base.Resolver.AccessToken;
import com.example.demo.base.Resolver.AuthorizationHeader;
import com.example.demo.base.common.AuthConstants;
import com.example.demo.bounded_context.account.dto.AccountInfoDto;
import com.example.demo.bounded_context.account.dto.AccountInfoRequest;
import com.example.demo.bounded_context.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "access / refresh token 을 사용한 로그아웃")
    public ResponseEntity signOut(@AuthorizationHeader AccessToken accessToken,
                                  @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken) {
        accountService.signOut(accessToken, refreshToken);
        return ResponseEntity.ok().body("로그아웃에 성공했습니다.");
    }

    @DeleteMapping("/withdrawal")
    @Operation(summary = "회원 탈퇴", description = "access / refresh token 을 사용한 회원 탈퇴")
    public ResponseEntity withdrawal(@AuthorizationHeader AccessToken accessToken,
                                         @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken) {
        accountService.withdrawal(accessToken, refreshToken);
        return ResponseEntity.ok().body("회원탈퇴에 성공했습니다.");
    }

    @GetMapping("/me")
    @Operation(summary = "정보 조회", description = "access token 을 사용한 사용자 정보 조회")
    public ResponseEntity<AccountInfoDto> read(@AuthorizationHeader Long id) {
        return ResponseEntity.ok().body(AccountInfoDto.of(accountService.read(id)));
    }

    @PutMapping("/me")
    @Operation(summary = "정보 수정", description = "access token 을 사용한 사용자 정보 수정")
    public ResponseEntity update(@AuthorizationHeader Long id, @RequestBody AccountInfoRequest request) {
        return ResponseEntity.ok().body(AccountInfoDto.of(accountService.update(id, request)));
    }
}
