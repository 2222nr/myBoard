package com.example.myBoard.controller;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.service.ArticleService;
import com.example.myBoard.service.PaginationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    PaginationService paginationService;
    private final ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String allView(Model model) {
        List<ArticleDto> articleDto = articleService.ArticleAllList();
        model.addAttribute("ArticleDto", articleDto);
        return "articles/show_all";
    }
    @GetMapping("paging")
    public String allView(Model model,
                          @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//        List<ArticleDto> articleDto = articleService.ArticleAllList();
//        model.addAttribute("ArticleDto",articleDto);
        // 넘겨온 페이지 번호로 리스트 받아오기
        Page<Article> paging = articleService.pagingList(pageable);
        // 페이지를 블럭 처리
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), totalPage);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);
        return "articles/show_all_list";
    }
}

