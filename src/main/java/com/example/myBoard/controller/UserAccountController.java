package com.example.myBoard.controller;

import com.example.myBoard.dto.UserCreateForm;
import com.example.myBoard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAccountController {
    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("signup")
    public String signup(UserCreateForm userCreateForm){
//        model.addAttribute("userCreateForm", new UserCreateForm());
        return "signup";
    }
    @PostMapping("signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult){
        System.out.println(userCreateForm);
        if(bindingResult.hasErrors()){
            return "signup";
        }
        userService.createUser(userCreateForm);
        return "redirect:/";
    }
    @GetMapping("login")
    public String login(){
//        model.addAttribute("userCreateForm", new UserCreateForm());
        return "login";
    }
}
