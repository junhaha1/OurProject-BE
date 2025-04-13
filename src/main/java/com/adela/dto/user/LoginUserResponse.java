package com.adela.dto.user;

import com.adela.domain.UserEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LoginUserResponse {
    private final String userId;
    private final String name;

    public LoginUserResponse(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
    }
}
