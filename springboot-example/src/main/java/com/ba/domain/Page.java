package com.ba.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pages")
public class Page implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String content;

    private String chapter;

    public Page() {
    }

    public Page(int number, String content, String chapter) {
        this.number = number;
        this.content = content;
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", number=" + number +
                ", content='" + content + '\'' +
                ", chapter='" + chapter + '\'' +
                '}';
    }
}
