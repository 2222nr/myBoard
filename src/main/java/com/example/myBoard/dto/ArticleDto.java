package com.example.myBoard.dto;

import com.example.myBoard.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String content;

    public static ArticleDto fromArticleEntity(Article article){
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

    public Article fromArticleDto(ArticleDto articleDto){
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        return article;
    }
}
