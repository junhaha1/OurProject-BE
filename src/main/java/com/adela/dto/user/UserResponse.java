package com.adela.dto.user;

import com.adela.domain.UserEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {
    private final String userId;
    private final String name;
    private final String pwd;
    private final LocalDate regDate;

    public UserResponse(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.pwd = userEntity.getPwd();
        this.regDate = userEntity.getRegDate();

    }
}
