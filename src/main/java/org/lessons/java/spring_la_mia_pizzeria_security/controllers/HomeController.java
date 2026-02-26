package org.lessons.java.spring_la_mia_pizzeria_security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getIndex(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "index";
    }

}
