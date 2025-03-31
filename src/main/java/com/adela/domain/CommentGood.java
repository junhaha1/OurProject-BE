package com.adela.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "commentgood")
public class CommentGood {

    @EmbeddedId
    private CommentGoodId id;

    @MapsId("commentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    private BoardComment boardComment;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @Builder
    public CommentGood(BoardComment boardComment, UserEntity userEntity){
        this.id = new CommentGoodId(boardComment.getCommentId(), userEntity.getUserId());
        this.boardComment = boardComment;
        this.userEntity = userEntity;
    }
}
