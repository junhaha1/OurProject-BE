package com.adela.repository;

import com.adela.domain.Article;
import com.adela.domain.ArticleGood;
import com.adela.domain.ArticleGoodId;
import com.adela.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleGoodRepository extends JpaRepository<ArticleGood, ArticleGoodId> {
    int countByBoardId(Article boardId);
    boolean existsByUserIdAndBoardId(UserEntity user, Article board);
}
