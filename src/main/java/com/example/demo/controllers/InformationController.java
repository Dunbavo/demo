package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController { //класс, отвещающий за обработку всех переходов по сайту (@Controller)

    @GetMapping("/information") //казание того, кокой url адресс обрабатываем.
    public String information(Model model) { //вызов функции и указание параметра model, который всегда принимается
        model.addAttribute("title", "Информация");//указание параметра "title", который будет передан в шаблон и текста "Информация"
        return "information"; //вызов шаблона "information"

    }

}
