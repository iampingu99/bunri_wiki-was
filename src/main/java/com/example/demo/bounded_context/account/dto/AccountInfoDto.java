package com.example.demo.bounded_context.account.dto;

import com.example.demo.bounded_context.account.entity.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountInfoDto {
    String nickname;
    Double latitude;
    Double longitude;

    @Builder
    public AccountInfoDto(String nickname, Double latitude, Double longitude) {
        this.nickname = nickname;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static AccountInfoDto of(Account account){
        return AccountInfoDto.builder()
                .nickname(account.getNickname())
                .latitude(account.getLatitude())
                .longitude(account.getLongitude())
                .build();
    }
}
