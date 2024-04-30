package com.example.myBoard.controller;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String delete(@PathVariable("deleteId") Long id,
                         RedirectAttributes redirectAttributes){
        // 1. 삭제할 대상이 존재하는지 체크
        ArticleDto articleDto = articleService.detailView(id);
        // 2. 대상 entity가 존재하면 삭제처리 후 메세지 전송
        if(articleDto != null){
            articleService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "정상적으로 삭제되었습니다.");
        }
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
