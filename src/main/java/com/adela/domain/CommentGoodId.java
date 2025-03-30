package com.adela.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommentGoodId implements Serializable {
    private Long commentId;
    private String userId;

    public CommentGoodId(Long commentId, String userId) {
        this.commentId = commentId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentGoodId)) return false;
        CommentGoodId that = (CommentGoodId) o;
        return Objects.equals(commentId, that.commentId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId);
    }
}
