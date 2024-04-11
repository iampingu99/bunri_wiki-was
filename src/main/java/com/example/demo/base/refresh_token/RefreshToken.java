package com.example.demo.base.refresh_token;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RefreshToken {
    @Id
    private Long accountId;
    private String token;
    private Date timeToLive;

    @Builder
    public RefreshToken(Long accountId, String token, Date timeToLive) {
        this.accountId = accountId;
        this.token = token;
        this.timeToLive = timeToLive;
    }

    public void reIssueToken(String token, Date timeToLive){
        this.token = token;
        this.timeToLive = timeToLive;
    }
}
