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

    @GetMapping("/good/{articleId}")
    public ResponseEntity<Integer> countGood(@PathVariable Long articleId){
        int count = goodService.countGood(articleId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/good/{articleId}/{userId}")
    public ResponseEntity<Boolean> searchGood(@PathVariable Long articleId, @PathVariable String userId){
        boolean exists = goodService.searchGood(articleId, userId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/good")
    public ResponseEntity<ArticleGood> addGood(@RequestBody AddGoodRequest request){
        ArticleGood savedArticleGood = goodService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticleGood);
    }

    @DeleteMapping("/good/{articleId}/{userId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") Long boardId, @PathVariable("userId") String userId){
        goodService.delete(userId, boardId);
        return ResponseEntity.ok()
                .build();
    }

}
