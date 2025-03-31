package com.adela.service;

import com.adela.domain.BoardComment;
import com.adela.dto.comment.AddCommnetRequest;
import com.adela.dto.comment.UpdateCommentRequest;
import com.adela.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    public final CommentRepository commentRepository;

    //새 댓글 저장하기
    public BoardComment save(AddCommnetRequest request) {
        return commentRepository.save(request.toEntity());
    }

    //댓글 삭제하기
    public void delete(long id){
        commentRepository.deleteById(id);
    }

    //게시글 ID를 통해 해당 게시글에 달린 댓글들 조회해오기
    public List<BoardComment> findByBoardId(long boardId) {
        return commentRepository.findByArticle_BoardId(boardId);
    }

    //영속성 컨텍스트를 이용하여 댓글 수정하기
    @Transactional
    public BoardComment update(long id, UpdateCommentRequest request){
        BoardComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        comment.update(request.getComment(), request.getCodeComment(), LocalDate.now());

        return comment;
    }
}
