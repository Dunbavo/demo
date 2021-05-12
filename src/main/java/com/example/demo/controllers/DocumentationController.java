package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentationController { //класс, отвещающий за обработку всех переходов по сайту (@Controller)

    @GetMapping("/documentation") //казание того, кокой url адресс обрабатываем.
    public String documentation(Model model) { //вызов функции и указание параметра model, который всегда принимается
        model.addAttribute("title", "Документы");//указание параметра "title", который будет передан в шаблон и текста "Документы"
        return "documentation"; //вызов шаблона "documentation"

    }

}
