package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import com.example.demo.services.ActivityService;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@RestController
@Controller
public class MainController { //класс, отвещающий за обработку всех переходов по сайту (@Controller)

    @Autowired private PostService postService; //указание репозитория, к которому обращаемся и название пееременной
    @Autowired private ActivityService activityService;

    @GetMapping("/") //казание того, кокой url адресс обрабатываем. ("/") - главная страница
    public String home(Model model) { //вызов функции и указание параметра model, который всегда принимается
        model.addAttribute("title", "Главная страница");//указание параметра "title", который будет передан в шаблон и текста "Главная страница"
        model.addAttribute("posts", postService.findAll()); //передача значений
        model.addAttribute("activities", activityService.findAll()); //передача значений
//        model.addAttribute("activities", activityService.findAll(0,6).getContent()); //передача значений findAll(0,6).getContent())
        return "home"; //вызов шаблона "home"
        /*при переходе на главную страницу будет вызываться функция home и передаётся параметр model.
        с помощью параметра model указываем данные, котрые можем передпть внутрь шаблона ("title", "Главная страница")*/
    }

    @Configuration
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/css/**")
                    .addResourceLocations("classpath:/css/");
        }
    }

}
