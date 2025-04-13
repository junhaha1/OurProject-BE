package com.adela.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24)//1일(24시간)  // 7일 60 * 60 * 24 * 7
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String userId;  // 혹은 username
    private String token;
}
