package com.adela.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "userentity")
public class UserEntity implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
