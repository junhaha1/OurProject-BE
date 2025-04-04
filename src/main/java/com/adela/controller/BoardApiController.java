package com.adela.controller;

import com.adela.domain.Article;
import com.adela.domain.BoardComment;
import com.adela.domain.UserEntity;
import com.adela.dto.article.AddArticleRequest;
import com.adela.dto.article.UpdateArticleRequest;
import com.adela.dto.comment.AddCommnetRequest;
import com.adela.dto.comment.CommentResponse;
import com.adela.dto.comment.UpdateCommentRequest;
import com.adela.service.BoardService;
import com.adela.service.CommentGoodService;
import com.adela.service.CommentService;
import com.adela.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.adela.dto.article.ArticleResponse;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardApiController {
    private final BoardService boardService;
    private final CommentService commentService;
    private final UserService userService;
    private final CommentGoodService commentGoodService;

    @PostMapping("/article/{userId}")
    public ResponseEntity<Article> addArticle(@PathVariable String userId, @RequestBody AddArticleRequest request) {
        request.connectionUserEntity(userService.findById(userId));
        Article savedArticle = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/article/list")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = boardService.findAll()
                .stream()
                .map(article -> new ArticleResponse(article, boardService.getLikeCount(article)))
                .toList();

        return ResponseEntity.ok().body(articles);
    }


    @GetMapping("/article/list/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("articleId") long id){
        Article article = boardService.findById(id);
        int likeCount = boardService.getLikeCount(article);
        return ResponseEntity.ok().body(new ArticleResponse(article, likeCount));
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") long id){
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/article/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable("articleId") long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

    //댓글 기능
    //댓글 추가
    @PostMapping("/comment/{articleId}/{userId}")
    public ResponseEntity<BoardComment> addComment(@PathVariable Long articleId, @PathVariable String userId, @RequestBody AddCommnetRequest request) {
        request.connectionArticle(boardService.findById(articleId));
        UserEntity user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("유저가 존재하지 않음: " + userId);
        }
        request.connectionUserEntity(user);
        BoardComment savedComment = commentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") long comment_id){
        commentService.delete(comment_id);
        return ResponseEntity.ok()
                .build();
    }

    //댓글 조회
    @GetMapping("/comment/list/{articleId}")
    public ResponseEntity<List<CommentResponse>> findByBoardIdComments(@PathVariable("articleId") long boardId) {
        List<CommentResponse> comments = commentService.findByBoardId(boardId)
                .stream()
                .map(comment -> new CommentResponse(comment, commentGoodService.goodCount(comment.getCommentId())))
                .toList();
        return ResponseEntity.ok().body(comments);
    }

    //댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateArticle(@PathVariable("commentId") long id, @RequestBody UpdateCommentRequest request){
        BoardComment updateBoardComment = commentService.update(id, request);

        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }
}
