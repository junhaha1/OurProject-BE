package com.adela.dto;

import com.adela.domain.Article;
import com.adela.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private UserEntity userEntity;
    private String title;
    private int ctId;
    private String content;
    private String codeContent;
    private String errorContent;
    private LocalDate regDate;

    public void connectionUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public Article toEntity(){
        return Article.builder()
                .userEntity(userEntity)
                .title(title)
                .ctId(ctId)
                .content(content)
                .codeContent(codeContent)
                .errorContent(errorContent)
                .regDate(regDate)
                .build();
    }
}