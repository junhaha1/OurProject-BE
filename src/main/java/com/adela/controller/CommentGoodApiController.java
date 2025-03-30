package com.adela.controller;

import com.adela.dto.AddCommentGoodRequest;
import com.adela.service.CommentGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("board/comment")
@RequiredArgsConstructor
@RestController
public class CommentGoodApiController {
    private final CommentGoodService commentGoodService;

    @PostMapping("/addgood")
    public ResponseEntity<String> commentSave(@RequestBody AddCommentGoodRequest request){
        commentGoodService.commentSave(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removegood/{commentId}/{userId}")
    public ResponseEntity<String> commentRemove(@PathVariable Long commentId, @PathVariable String userId){
        commentGoodService.commentRemove(commentId, userId);
        return ResponseEntity.ok().build();
    }
}
