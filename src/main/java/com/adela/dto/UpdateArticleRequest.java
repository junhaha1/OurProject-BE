package com.adela.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;
    private int ctId;
    private String codeContent;
    private String errorContent;
}
