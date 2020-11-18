package com.ba.service;

import com.ba.domain.News;
import com.ba.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    public String addNewNews() {
        News news = new News("Bakan istifa etti","siyaset","ekonomi bakanı istifa etti");

        newsRepository.save(news);

        return news.toString();
    }

    public List<News> findAllNewsList() {
        return newsRepository.findAll();
    }

    public News findNewsById(Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);

        if (!optionalNews.isPresent()) {
            return null;
        }

        return optionalNews.get();
    }

    public String deleteNewsById(Long id) {
        newsRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }

    public List<String> findAllNewsCategoryList() {
        return newsRepository.getAllUniqueCategories();
    }
}
