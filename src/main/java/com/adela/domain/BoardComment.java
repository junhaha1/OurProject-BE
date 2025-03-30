package com.adela.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boardcomment")
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commnet_id", nullable = false)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY) //나중에 확인
    @JoinColumn(name="board_id", nullable=false)
    private Article article;

    @Column(name = "content", nullable = false)
    private String comment;

    @Column(name = "code_content")
    private String codeComment;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Builder
    public BoardComment(String comment, Article article, String codeComment, LocalDate regDate, LocalDate updateDate) {
        this.comment = comment;
        this.article = article;
        this.codeComment = codeComment;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    public void update(String comment, String codeContent, LocalDate updateDate) {
        this.comment = comment;
        this.codeComment = codeContent;
        this.updateDate = updateDate;
    }
}
