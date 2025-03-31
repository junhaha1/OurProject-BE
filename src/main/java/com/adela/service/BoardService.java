package com.adela.service;

import com.adela.domain.Article;
import com.adela.dto.AddArticleRequest;
import com.adela.dto.UpdateArticleRequest;
import com.adela.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public Article save(AddArticleRequest request){
        return boardRepository.save(request.toEntity());
    }

    public List<Article> findAll () {
        return boardRepository.findAll();
    }

    //게시판 ID를 이용하여 해당 게시글 상세조회하기
    public Article findById(long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    //게시글 ID로 삭제
    public void delete(long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getCtId(), request.getContent(), request.getCodeContent(), request.getErrorContent(), LocalDate.now());

        return article;
    }
}