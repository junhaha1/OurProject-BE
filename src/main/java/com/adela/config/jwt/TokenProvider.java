package com.adela.config.jwt;

import com.adela.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    //사용자 정보를 이용하여 토큰을 만드는 메소드
    public String generateToken(UserEntity user, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    //실제 토큰을 만드는 메소드
    private String makeToken(Date expiry, UserEntity user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getUserId())
                .claim("id", user.getUserId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //토근이 유효한지 검증하는 메서드
    public boolean validToken(String token){
        try{
            //가져온 토큰을 yml에 있는 secretKey와 함께 복호화 => 에러가 발생하면 유효하지 않은 토큰이란 의미
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //토큰 기반으로 인증 정보를 가져오는 메서드 -> 토큰을 받아 인증 정보를 담은 객체 Authentication을 반환
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        //사용자 ID가 들어있는 토큰 제목 sub와 토큰을 기반으로 인증 정보를 생성
        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(),
                "",authorities), token, authorities);
    }

    //토큰 기반으로 사용자 ID를 가져오는 메서드
    public String getUserId(String token){
        Claims claims = getClaims(token);
        //복호화한 토큰에서 반환받은 클래임에서 "id" 키로 저장된 값을 가져와 반환
        return claims.get("id", String.class);
    }

    //프로퍼티즈(yml) 파일에 저장한 비밀값으로 토큰을 복호화한 뒤 클레임을 반환
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
