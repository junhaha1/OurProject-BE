package com.adela.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "userentity")
public class UserEntity {

    //email
    @Id
    @Column(name = "user_id", updatable = false)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "del_date")
    private LocalDate deleteDate;

    @Builder
    public UserEntity(String userId, String name, String pwd, LocalDate regDate){
        this.userId = userId;
        this.name = name;
        this.pwd = pwd;
        this.regDate = regDate;
    }

    public void update(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }
}
