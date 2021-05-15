package com.example.demo.controllers;

import com.example.demo.models.Activity;
import com.example.demo.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class ActivityController {

    @Autowired //анотация для создания переменной, ссылающейся на репозиторий
    private ActivityService activityService; //указание репозитория, к которому обращаемся и название пееременной

    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("activities", activityService.findAll()); //передача значений
        model.addAttribute("title", "Мероприятия");
        return "activity";
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/activity/add") //GetMapping - пользователь переходит по определённому адресу
    public String activityAdd(Model model) {
        return "activity-add";
    }

    @RequestMapping(value = "/activity/add",method = RequestMethod.POST) //получение данных из формы
    public String activityActivityAdd(@RequestParam String img, @RequestParam String title, @RequestParam String anons, @RequestParam Date data, @RequestParam String fullText, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Activity activity = new Activity(img, title, anons, data, fullText); //объект на основе модели Post с названием post. (title, anons, fullText) - передача параметров
        activityService.save(activity); //сохранение объекта и добавление в бд -> обращение к репозиторию -> обращение к функции save и передача в него объекта, который необходимо сохранить => добавление в таблицу post навых статей, полученных от пользователя
        //postRepository.save(post);
        return "redirect:/activity"; //переадресация пользователя на указанную страницу после добавления статьи
    }

    @GetMapping("/activity/{id}") //{id} - динамическое значение url-адреса
    public String activityDetails(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        Optional<Activity> activity= activityService.findById(id); //нахождение записи по id и помещение в объект post на основе класса Optional и модели <Post>
        if(activity.isPresent()) {
            ArrayList<Activity> res = new ArrayList<>();
            activity.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
            model.addAttribute("activity", res);
            return "activity-details";
        } else {
            return "redirect:/activity"; //перенаправление на указанную страницу
        }
    }

    @GetMapping("/activity/{id}/edit") //редактирование статьи
    public String activityEdit(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        if(!activityService.existsById(id)){ //try - если определённая запись по определённому id не была найдена. иначе false
            return "redirect:/activity"; //перенаправление на указанную страницу
        }
        //Optional<Post> post= postRepository.findById(id);
        Optional<Activity> post= activityService.findById(id); //нахождение записи по id и помещение в объект post на основе класса Optional и модели <Post>
        ArrayList<Activity> res = new ArrayList<>();
        post.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
        model.addAttribute("activity", res);
        return "activity-edit";
    }

    @PostMapping("/activity/{id}/edit") //получение данных из формы
    public String activityActivityUpdate(@PathVariable(value = "id") long id, @RequestParam String img, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, @RequestParam Date data, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Activity activity = activityService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        activity.setImg(img);
        activity.setTitle(title); //установка введеного заголовка
        activity.setAnons(anons);
        activity.setFullText(fullText);
        activity.setData(data);
        activityService.save(activity); //сохранение обновлённого объекта
        return "redirect:/activity/{id}"; //переадресация пользователя на указанную страницу после добавления статьи
    }

    @PostMapping("/activity/{id}/remove") //получение данных из формы
    public String newsActivityDelete(@PathVariable(value = "id") long id, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Activity activity = activityService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        activityService.delete(activity); //удаление определенной записи
        return "redirect:/activity"; //переадресация пользователя на указанную страницу после удаления статьи
    }
}
