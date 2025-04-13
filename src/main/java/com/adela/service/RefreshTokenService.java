package com.adela.service;

import com.adela.domain.RefreshToken;
import com.adela.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService { //리프레시 토큰을 레디스에서 조회하여 넘겨주는 서비스
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String userId, String refreshToken){
        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));
    }

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
