package com.adela.controller;

import com.adela.domain.UserEntity;
import com.adela.dto.user.AddUserRequest;
import com.adela.dto.user.UpdateUserRequest;
import com.adela.dto.user.UserResponse;
import com.adela.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> addUser(@RequestBody AddUserRequest request){
        if (request.getRegDate() == null) {
            request.setRegDate(LocalDate.now());
        }
        UserEntity savedUserEntity = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUserEntity);
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
