package com.adela.repository;

import com.adela.domain.CommentGood;
import com.adela.domain.CommentGoodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentGoodRepository extends JpaRepository<CommentGood, CommentGoodId> {
    int countById_CommentId(Long commentId);
}
