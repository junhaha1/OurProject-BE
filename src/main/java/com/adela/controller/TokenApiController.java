package com.adela.controller;

import com.adela.dto.jwt.CreateAccessTokenRequest;
import com.adela.dto.jwt.CreateAccessTokenResponse;
import com.adela.dto.user.UpdateUserRequest;
import com.adela.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
    
    
    //테스트 코드
    @PostMapping("/token/create")
    public ResponseEntity<List<String>> createUserToken(@RequestBody UpdateUserRequest request){
        List<String> tokens = tokenService.createTokens(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tokens);
    }
}
