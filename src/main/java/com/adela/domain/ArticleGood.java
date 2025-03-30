package com.adela.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ArticleGoodId.class) // 복합 키 설정
@Table(name = "boardgood")
public class ArticleGood {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Article boardId;

    @Builder
    public ArticleGood(UserEntity userId, Article boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }
}
