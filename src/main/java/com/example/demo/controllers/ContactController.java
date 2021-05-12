package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController { //класс, отвещающий за обработку всех переходов по сайту (@Controller)

    @GetMapping("/contact") //казание того, кокой url адресс обрабатываем.
    public String contact(Model model) { //вызов функции и указание параметра model, который всегда принимается
        model.addAttribute("title", "Связь");//указание параметра "title", который будет передан в шаблон и текста "Связь"
        return "contact"; //вызов шаблона "contact"

    }

}
