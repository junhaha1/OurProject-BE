package com.adela.dto.article;

import com.adela.domain.Article;
import com.adela.domain.ArticleGood;
import com.adela.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddGoodRequest {
    private String userId;
    private Long boardId;

    public ArticleGood toEntity(UserEntity user, Article board) {
        return ArticleGood.builder()
                .userId(user)
                .boardId(board)
                .build();
    }

}
