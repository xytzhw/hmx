package com.hmx.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping("/")
    public String index(String username, String password, Model model)
    {
        model.addAttribute("username", username);
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
}
