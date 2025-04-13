package com.adela.controller;

import com.adela.domain.UserEntity;
import com.adela.dto.user.*;
import com.adela.service.RefreshTokenService;
import com.adela.service.TokenService;
import com.adela.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class UserApiController {
    private final UserService userService;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> addUser(@RequestBody AddUserRequest request){
        if (request.getRegDate() == null) {
            request.setRegDate(LocalDate.now());
        }
        UserEntity savedUserEntity = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUserEntity);
    }

    //로그인 요청
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request, HttpServletResponse response){
        //1. 아이디, 비밀번호가 틀렸을 경우
        try{
            UserEntity user = userService.login(request.getUserId(), request.getPwd());

            String accessToken = tokenService.createToken(user, 1);
            String refreshToken = tokenService.createToken(user, 24);

            refreshTokenService.saveRefreshToken(user.getUserId(), refreshToken);

            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setSecure(false); // HTTPS 환경에서만 동작, 로컬 개발 중이면 false로 설정해도 됨
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7일

            response.addCookie(refreshCookie);

            return ResponseEntity.ok(Map.of(
                    "accessToken", accessToken,
                    "user", new LoginUserResponse(user))); // 확인하기
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable("userId") String userId){
        UserEntity userEntity = userService.findById(userId);

        return ResponseEntity.ok()
                .body(new UserResponse(userEntity));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){
        userService.delete(userId);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserRequest request){
        UserEntity updateUserEntity = userService.update(userId, request);

        return ResponseEntity.ok()
                .body(updateUserEntity);
    }

}
