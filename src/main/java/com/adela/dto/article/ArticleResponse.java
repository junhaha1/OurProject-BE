package com.adela.dto.article;

import com.adela.domain.Article;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ArticleResponse {
    private final Long boardId;
    private final String userId;
    private final String title;
    private final int ctId;
    private final String content;
    private final String codeContent;
    private final String errorContent;
    private final LocalDate regDate;
    private final LocalDate updateDate;

    private final int likeCount;

    public ArticleResponse(Article article, int likeCount) {
        this.boardId = article.getBoardId();
        this.userId = article.getUserEntity().getUserId();
        this.title = article.getTitle();
        this.ctId = article.getCtId();
        this.content = article.getContent();
        this.codeContent = article.getCodeContent();
        this.errorContent = article.getErrorContent();
        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();

        this.likeCount = likeCount;
    }
}
