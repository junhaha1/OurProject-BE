package com.adela.service;

import com.adela.domain.UserEntity;
import com.adela.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


//토큰 발급하기 위한 사용자 조회
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException((userId)));
    }
}
