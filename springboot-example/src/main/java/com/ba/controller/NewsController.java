package com.ba.controller;

import com.ba.domain.News;
import com.ba.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "News Operations")
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService service;

    @GetMapping("/add")
    @ApiOperation(value = "Add news", notes = "Generate News Object and Add to DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Record created successfully"),
            @ApiResponse(code = 409, message = "ID already taken")
    })
    public String addNews() {
        return service.addNewNews();
    }

    @GetMapping("/list")
    @ApiOperation(value = "Read all news matching given filter", notes = "Will get all the news")
    public List<News> getAllNews() {
        return service.findAllNewsList();
    }

    @GetMapping("/list/categories")
    @ApiOperation(value = "Read all news categories", notes = "Will get all the news categories")
    public List<String> getAllNewsCategories() {
        return service.findAllNewsCategoryList();
    }

    @ApiOperation(value = "Read  news matching given id", notes = "Will get  the news for the given id")
    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return service.findNewsById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete  news matching given id", notes = "Will delete  the news for the given id")
    public String deleteNews(@PathVariable Long id) {
        return service.deleteNewsById(id);
    }
}
