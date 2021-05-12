package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
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

    @Autowired //анотация для создания переменной, ссылающейся на репозиторий
    private PostService postService; //указание репозитория, к которому обращаемся и название пееременной


    @GetMapping("/") //казание того, кокой url адресс обрабатываем. ("/") - главная страница
    public String home(Model model) { //вызов функции и указание параметра model, который всегда принимается
        model.addAttribute("title", "Главная страница");//указание параметра "title", который будет передан в шаблон и текста "Главная страница"
        model.addAttribute("posts", postService.findAll()); //передача значений
        return "home"; //вызов шаблона "home"
        /*при переходе на главную страницу будет вызываться функция home и передаётся параметр model.
        с помощью параметра model указываем данные, котрые можем передпть внутрь шаблона ("title", "Главная страница")*/
    }

    @GetMapping("/news/add") //GetMapping - пользователь переходит по определённому адресу
    public String newsAdd(Model model) {
        return "news-add";
    }

    @PostMapping("/news/add") //получение данных из формы
    public String newsPostAdd(@RequestParam String img, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Post post = new Post(img, title, anons, fullText); //объект на основе модели Post с названием post. (title, anons, fullText) - передача параметров
        postService.save(post); //сохранение объекта и добавление в бд -> обращение к репозиторию -> обращение к функции save и передача в него объекта, который необходимо сохранить => добавление в таблицу post навых статей, полученных от пользователя
        //postRepository.save(post);
        return "redirect:/news"; //переадресация пользователя на указанную страницу после добавления статьи
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
