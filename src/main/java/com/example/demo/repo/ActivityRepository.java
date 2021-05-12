package com.example.demo.repo;

import com.example.demo.models.Activity;
import org.springframework.data.repository.CrudRepository; //интерфейс позволяющий работать с таблицами

public interface ActivityRepository extends CrudRepository<Activity, Long> {  //extends - наследование. <> - указание модели, с которой работаем и типа данных
}
