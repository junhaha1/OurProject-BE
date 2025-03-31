package com.adela.repository;

import com.adela.domain.ArticleGood;
import com.adela.domain.ArticleGoodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<ArticleGood, ArticleGoodId> {

}
