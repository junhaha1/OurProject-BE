package com.adela.service;

import com.adela.config.jwt.TokenProvider;
import com.adela.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public List<String> createTokens(String userId){
        UserEntity user = userService.findById(userId);
        List<String> tokens = new ArrayList<String>();

        tokens.add(tokenProvider.generateToken(user, Duration.ofHours(2))); //accessToken
        String refreshToken = tokenProvider.generateToken(user, Duration.ofHours(24));
        tokens.add(refreshToken); //refreshToken

        refreshTokenService.saveRefreshToken(user.getUserId(), refreshToken);

        return tokens;
    }

    //access토큰이 만료됐을 경우 리프레시 토큰을 통해 사용자가 맞는지 확인 후에 재발급
    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){ //유효성 검사 실패 시에 예외 보냄
            throw new IllegalArgumentException("Unexpected token");
        }

        String userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        UserEntity user = userService.findById(userId);

        return  tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
