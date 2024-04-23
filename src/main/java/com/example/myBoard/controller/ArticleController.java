package com.example.myBoard.controller;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("insert")
    public String insertView(Model model){
        model.addAttribute("articleDto", new ArticleDto());
        return "articles/new";
    }

    @PostMapping("insert")
    public String insert(@ModelAttribute("articleDto") ArticleDto articleDto){
        articleService.insertContent(articleDto);
        return "redirect:/";
    }

    @GetMapping("list")
    public String listView(Model model){
        List<ArticleDto> articleDto = articleService.ArticleAllList();
        model.addAttribute("ArticleDto",articleDto);
        return "articles/articleList";
    }
    @GetMapping("update")
    public String updateView(@RequestParam("updateId") Long id,
                             Model model){
        model.addAttribute("articleDto", articleService.updateView(id));
        return "articles/update";
    }

    @PostMapping("update")
    public String updateQuiz(@Valid @ModelAttribute("quizDto") ArticleDto articleDto,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "articles/update";
        }
        articleService.updateContent(articleDto);
        return "redirect:/";
    }

    @GetMapping("allList")
    public String allListView(Model model){
        List<ArticleDto> articleDto = articleService.ArticleAllList();
        model.addAttribute("ArticleDto",articleDto);
        return "articles/allList";
    }

    @GetMapping("detail/{id}")
    public String detailView(@PathVariable("id")Long id,
                             Model model){
        ArticleDto articleDto = articleService.detailView(id);
        model.addAttribute(articleDto);
        return "articles/detail";
    }

    @PostMapping("{deleteId}")
    public String delete(@PathVariable("deleteId") Long id){
        articleService.delete(id);
        return "redirect:/";
    }

    @GetMapping("search")
    public String searchMember(@RequestParam("type") String type,
                               @RequestParam("keyword") String keyword,
                               Model model){
        List<ArticleDto> articleDto = articleService.searchContent(type, keyword);
        model.addAttribute("ArticleDto", articleDto);
        return "articles/show_all";
    }
}
