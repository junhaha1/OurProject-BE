package com.adela.controller;

import com.adela.domain.ArticleGood;
import com.adela.dto.article.AddGoodRequest;
import com.adela.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class GoodApiController {
    private final GoodService goodService;

    @PostMapping("/good")
    public ResponseEntity<ArticleGood> addGood(@RequestBody AddGoodRequest request){
        ArticleGood savedArticleGood = goodService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticleGood);
    }

    @DeleteMapping("/good/{boardId}/{userId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId){
        goodService.delete(userId, boardId);
        return ResponseEntity.ok()
                .build();
    }

}
