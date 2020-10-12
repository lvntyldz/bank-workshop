package com.ba.controller;

import com.ba.domain.Todo;
import com.ba.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/add")
    public String addTodo() {
        return service.addNewTodo();
    }

    @GetMapping("/list")
    public List<Todo> getAllTodo() {
        return service.findAllTodoList();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return service.findTodoById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        return service.deleteTodoById(id);
    }
}
