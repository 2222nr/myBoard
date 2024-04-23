package com.example.myBoard.service;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;

    }

    public List<ArticleDto> ArticleAllList() {
        return articleRepository.findAll()
                .stream()
                .map(x -> ArticleDto.fromArticleEntity(x))
                .toList();
    }
    public ArticleDto detailView(Long id) {
        return articleRepository.findById(id).map(x -> ArticleDto.fromArticleEntity(x)).orElse(null);
    }

    public void insertContent(ArticleDto articleDto) {
        Article article = articleDto.fromArticleDto(articleDto);
        articleRepository.save(article);
    }


    public void updateContent(ArticleDto articleDto) {
        Article article = articleDto.fromArticleDto(articleDto);
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public List<ArticleDto> searchContent(String type, String keyword) {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        switch (type){
            case "title":
                articleDtoList=articleRepository.searchTitle(keyword)
                        .stream()
                        .map(x -> ArticleDto.fromArticleEntity(x))
                        .toList();
                break;
            case "content":
                articleDtoList=articleRepository.searchContent(keyword)
                        .stream()
                        .map(x -> ArticleDto.fromArticleEntity(x))
                        .toList();
                break;

            default:
                articleDtoList=articleRepository.searchQuery()
                        .stream()
                        .map(x -> ArticleDto.fromArticleEntity(x))
                        .toList();
                break;
        }
        return articleDtoList;
    }

    public ArticleDto updateView(Long id) {
        return articleRepository.findById(id).map(x -> ArticleDto.fromArticleEntity(x)).orElse(null);
    }

    public void insertArticle(ArticleDto articleDto){
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();
    }
}
