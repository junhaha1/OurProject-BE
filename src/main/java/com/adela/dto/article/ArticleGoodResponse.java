package com.adela.dto.article;

import com.adela.domain.ArticleGood;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleGoodResponse {
    private String userId;
    private Long boardId;
    private boolean liked;
}