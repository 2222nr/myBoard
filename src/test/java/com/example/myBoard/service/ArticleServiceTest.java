package com.example.myBoard.service;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest // 통합테스트
@Transactional // 각 테스트 진행시의 DB 값 롤백/ 단위테스트 시에는 각 메서드에 적용
@TestPropertySource(locations = "classpath:application-test.properties")
class ArticleServiceTest {
    @Autowired  // 자동주입
    ArticleService articleService;

    @Test
    @DisplayName("전체데이터_읽기")
    void articleAllList() {
        //Given
        int totalCount = 3;
        //When
        List<ArticleDto> articleDtoList = articleService.ArticleAllList();
        int actualCount = articleDtoList.size();
        //Then
        assertThat(actualCount).isEqualTo(totalCount);
    }

    @Test
    @DisplayName("단건자료검색_테스트")
    void detailView() {
        //Given
        ArticleDto expectDto = ArticleDto.builder().id(2L).title("나나나").content("222").build();
        //When
        ArticleDto actualDto = articleService.detailView(2L);
        //Then
        assertThat(actualDto.toString()).isEqualTo(expectDto.toString());
    }

    @Test
    @DisplayName("자료입력_테스트")
    void insertContent() {
        //Given
        ArticleDto expectDto = ArticleDto.builder().id(4L).title("라라라").content("444").build();
        //When
        articleService.insertContent(new ArticleDto(null, "라라라", "444"));
        //Then
        assertThat(articleService.detailView(4L).getTitle()).isEqualTo("라라라");
    }

    @Test
    @DisplayName("자료수정_테스트")
    void updateContent() {
        //Given
        ArticleDto expectDto = ArticleDto.builder().id(2L).title("타이틀수정").content("내용수정").build();
        //When
        ArticleDto actualDto = articleService.detailView(2L);
        articleService.updateContent(expectDto);
        //Then
        assertThat(actualDto.toString()).isNotEqualTo(expectDto.toString());

    }

    @Test
    @DisplayName("자료삭제_테스트")
    void delete() {
        //Given
        Long deleteId = 1L;
        //When
        articleService.delete(deleteId);
        //Then
//        assertThat(articleService.detailView(deleteId)).isEqualTo(null);
        ArticleDto articleDto = articleService.detailView(deleteId);
        assertThat(articleDto).isEqualTo(null);
    }

    @Test
    void searchContent() {
    }

    @Test
    void updateView() {
    }

    @Test
    void insertArticle() {
    }
}