package com.adela.controller;

import com.adela.dto.AddCommentGoodRequest;
import com.adela.dto.AddGoodRequest;
import com.adela.service.CommentGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @DeleteMapping("/removegood/{articleId}/{userId}")
    public ResponseEntity<String> commentRemove(@PathVariable Long articleId, @PathVariable String userId){
        commentGoodService.commentRemove(articleId, userId);
        return ResponseEntity.ok().build();
    }
}
