package com.adela.service;

import com.adela.domain.UserEntity;
import com.adela.dto.user.AddUserRequest;
import com.adela.dto.user.UpdateUserRequest;
import com.adela.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    //생성
    public UserEntity save(AddUserRequest request){
        return userRepository.save(request.toEntity());
    }
    
    //회원정보 조회 (본인정보)
    public UserEntity findById (String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + userId));
    }

    //삭제
    public void delete(String userId){
        userRepository.deleteById(userId);
    }

    //수정
    @Transactional  //매칭한 메서드를 하나의 트랜젝션으로 묶는 역할
    public UserEntity update(String userId, UpdateUserRequest request){
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + userId));
        userEntity.update(request.getName(), request.getPwd());

        return userEntity;
    }

}
