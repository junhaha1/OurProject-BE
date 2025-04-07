package com.adela.repository;

import com.adela.domain.Article;
import com.adela.domain.ArticleGood;
import com.adela.domain.ArticleGoodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleGoodRepository extends JpaRepository<ArticleGood, ArticleGoodId> {
    int countByBoardId(Article boardId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ArticleGood a WHERE a.boardId.boardId = :boardId AND a.userId.userId = :userId")
    boolean existsByBoardIdAndUserId(@Param("boardId") Long boardId, @Param("userId") String userId);
}
