package com.example.demo.repo;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository; //интерфейс позволяющий работать с таблицами

public interface PostRepository extends CrudRepository<Post, Long> { //extends - наследование. <> - указание модели, с которой работаем и типа данных
}
