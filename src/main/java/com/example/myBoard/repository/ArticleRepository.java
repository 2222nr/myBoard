package com.example.myBoard.repository;

import com.example.myBoard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select * from article order by age", nativeQuery = true)
    List<Article> searchQuery();
    @Query(value = "select * from article where title like %:keyword% order by id",
            nativeQuery = true)
    List<Article> searchTitle(@Param("keyword")String keyword);

    @Query(value = "select * from article where content like %:keyword% order by id",
            nativeQuery = true)
    List<Article> searchContent(@Param("keyword")String keyword);
}
