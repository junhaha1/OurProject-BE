package com.adela.service;

import com.adela.domain.Article;
import com.adela.domain.ArticleGood;
import com.adela.domain.ArticleGoodId;
import com.adela.domain.UserEntity;
import com.adela.dto.article.AddGoodRequest;
import com.adela.repository.BoardRepository;
import com.adela.repository.GoodRepository;
import com.adela.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GoodService {
    private final GoodRepository goodRepository;
    private final UserRepository userRepository;  // userEntity 조회용
    private final BoardRepository articleRepository;  // Article 조회용

    public ArticleGood save(AddGoodRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + request.getUserId()));

        Article board = articleRepository.findById(request.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + request.getBoardId()));

        return goodRepository.save(request.toEntity(user, board));  // 변환된 엔티티로 저장
    }

    public void delete(String userId, Long boardId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));

        Article board = articleRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + boardId));

        ArticleGoodId id = new ArticleGoodId();
        id.setUserId(user);
        id.setBoardId(board);
        goodRepository.deleteById(id);
    }
}
