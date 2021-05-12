package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NewsController {

    @Autowired //анотация для создания переменной, ссылающейся на репозиторий
    private PostService postService; //указание репозитория, к которому обращаемся и название пееременной
    private PostRepository postRepository;
    //private PostRepository postRepository;

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("posts", postService.findAll()); //передача значений
        //model.addAttribute("posts", postRepository.findAll());
        return "news";
    }

   @GetMapping("/news/{id}") //{id} - динамическое значение url-адреса
   public String newsDetails(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        Optional<Post> post= postService.findById(id); //нахождение записи по id и помещение в объект post на основе класса Optional и модели <Post>
        if(post.isPresent()) {
            ArrayList<Post> res = new ArrayList<>();
            post.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
            model.addAttribute("post", res);
            return "news-details";
        } else {
            return "redirect:/news"; //перенаправление на указанную страницу
        }
    }

    @GetMapping("/news/{id}/edit") //редактирование статьи
    public String newsEdit(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        if(!postService.existsById(id)){ //try - если определённая запись по определённому id не была найдена. иначе false
            return "redirect:/news"; //перенаправление на указанную страницу
        }
        //Optional<Post> post= postRepository.findById(id);
        Optional<Post> post= postService.findById(id); //нахождение записи по id и помещение в объект post на основе класса Optional и модели <Post>
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
        model.addAttribute("post", res);
        return "news-edit";
    }

    @PostMapping("/news/{id}/edit") //получение данных из формы
    public String newsPostUpdate(@PathVariable(value = "id") long id, @RequestParam String img, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Post post = postService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        post.setTitle(img);
        post.setTitle(title); //установка введеного заголовка
        post.setAnons(anons);
        post.setFullText(fullText);
        postService.save(post); //сохранение обновлённого объекта
        return "redirect:/news/{id}"; //переадресация пользователя на указанную страницу после добавления статьи
    }

    @PostMapping("/news/{id}/remove") //получение данных из формы
    public String newsPostDelete(@PathVariable(value = "id") long id, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Post post = postService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        postService.delete(post); //удаление определенной записи
        return "redirect:/news"; //переадресация пользователя на указанную страницу после удаления статьи
    }
}