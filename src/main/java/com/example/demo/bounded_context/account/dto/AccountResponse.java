package com.example.demo.bounded_context.account.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.dto.WikiListResponse;

import java.util.List;

public record AccountResponse(
        String accountName,
        String email,
        String nickname,
        Double latitude,
        Double longitude,
        List<WikiListResponse> wikis
) {

    public static AccountResponse fromEntity(Account account){
        return new AccountResponse(
                account.getAccountName(),
                account.getEmail(),
                account.getNickname(),
                account.getLatitude(),
                account.getLongitude(),
                account.getWikis().stream().map(WikiListResponse::fromEntity).toList()
                );
    }
}
