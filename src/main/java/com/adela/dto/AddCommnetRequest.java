package com.adela.dto;

import com.adela.domain.Article;
import com.adela.domain.BoardComment;
import com.adela.domain.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommnetRequest {
    private String comment;
    private Article article;
    private UserEntity userEntity;
    private String codeComment;
    private LocalDate regDate;
    private LocalDate updateDate;

    public void connectionArticle(Article article){
        this.article = article;
    }
    public void connectionUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public BoardComment toEntity(){
        return BoardComment.builder()
                .comment(comment)
                .article(article)
                .userEntity(userEntity)
                .codeComment(codeComment)
                .regDate(LocalDate.now())
                .updateDate(null)
                .build();
    }
}
