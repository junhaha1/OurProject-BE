package com.adela.dto;

import com.adela.domain.BoardComment;
import com.adela.domain.CommentGood;
import com.adela.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentGoodRequest {
    private Long commentId;
    private String userId;

    public CommentGood toEntity(BoardComment boardComment, UserEntity userEntity){
        return CommentGood.builder()
                .userEntity(userEntity)
                .boardComment(boardComment)
                .build();
    }
}
