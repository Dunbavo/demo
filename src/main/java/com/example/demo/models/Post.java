package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity //аннотация. указание того, что данный класс - модель, которая будет отвечать за отпределённую таблицу БД
public class Post {

    @Id //аннотация - уникальный идентификатор
    @GeneratedValue(strategy = GenerationType.AUTO) //аннотация - при добавлении новой записи позволит генерировать новые значения внутри данного поля (автоматически)
    private Long id; //поле - уникальные идентификатор (Long - тип данных)
    private String img, title, anons, fullText; // поле - название статьи, анонс, полный текс статьи
    private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg(){
        return img;
    }

    public  void setImg(String img){
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Post() {
    }

    public Post(String img, String title, String anons, String fullText, Date data) {
        this.img = img;
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
        this.data = data;
    }
}

