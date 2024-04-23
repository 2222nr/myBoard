package com.example.myBoard.controller;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final ArticleService articleService;
    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping("/")
    public String allView(Model model){
        List<ArticleDto> articleDto = articleService.ArticleAllList();
        model.addAttribute("ArticleDto",articleDto);
        return "articles/show_all";
    }
}
