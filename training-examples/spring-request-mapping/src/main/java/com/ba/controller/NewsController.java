package com.ba.controller;

import com.ba.dto.News;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    private List<News> allNews = new ArrayList<>();

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        Optional<News> optionalNews = allNews.stream().filter(news1 -> news1.getId() == id).findAny();

        if (!optionalNews.isPresent()) {
            System.out.println("sonuç bulunamadı");
            return null;
        }

        return optionalNews.get();

    }

    @GetMapping("/list")
    public List<News> listAllNews() {
        return allNews;
    }

    @PostMapping("/add")
    public News addNews(@RequestBody News news) {
        allNews.add(news);
        return news;
    }

    @PutMapping("/update/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News news) {
        Optional<News> optionalNews = allNews.stream().filter(news1 -> news1.getId() == id).findAny();

        if (optionalNews == null) {
            System.out.println("girilen ID ile haber bulunamadı!");
            return null;
        }

        optionalNews.get().setTitle(news.getTitle());
        optionalNews.get().setDescription(news.getDescription());

        return news;
    }

    @DeleteMapping("/{id}")
    public List<News> deleteNews(@PathVariable Long id) {

        allNews.removeIf(news -> news.getId() == id);
        return allNews;
    }

}
