package com.adela.service;

import com.adela.domain.BoardComment;
import com.adela.domain.CommentGoodId;
import com.adela.domain.UserEntity;
import com.adela.dto.AddCommentGoodRequest;
import com.adela.repository.CommentGoodRepository;
import com.adela.repository.CommentRepository;
import com.adela.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentGoodService {
    private final CommentGoodRepository commentGoodRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void commentSave(AddCommentGoodRequest request){
        BoardComment boardComment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다: " + request.getCommentId()));
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + request.getUserId()));

        //만약 해당 객체가 없는 경우에도 생각해줘야 함.
        commentGoodRepository.save(request.toEntity(boardComment, userEntity));
    }

    public void commentRemove(Long commentId, String userId){
        CommentGoodId commentGoodId = new CommentGoodId(commentId, userId);
        if (!commentGoodRepository.existsById(commentGoodId)) {
            throw new IllegalStateException("해당 댓글엔 좋아요 정보가 존재하지 않습니다.");
        }
        commentGoodRepository.deleteById(commentGoodId);
    }
}
