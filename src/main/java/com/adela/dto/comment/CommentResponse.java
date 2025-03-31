package com.adela.dto.comment;

import com.adela.domain.BoardComment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponse {
    private Long commentId;
    private String comment;
    private String codeComment;
    private LocalDate regDate;
    private LocalDate updateDate;

    public CommentResponse(BoardComment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.codeComment = comment.getCodeComment();
        this.regDate = comment.getRegDate();
        this.updateDate = comment.getUpdateDate();
    }
}
