package com.ba.controller;

import com.ba.domain.Todo;
import com.ba.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Todo Operations")
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/add")
    @ApiOperation(value = "Add todo", notes = "Generate Todo Object and Add to DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Record created successfully"),
            @ApiResponse(code = 409, message = "ID already taken")
    })
    public String addTodo() {
        return service.addNewTodo();
    }

    @GetMapping("/list")
    @ApiOperation(value = "Read all todos matching given filter", notes = "Will get all the todos")
    public List<Todo> getAllTodo() {
        return service.findAllTodoList();
    }

    @ApiOperation(value = "Read  todo matching given id", notes = "Will get  the todo for the given id")
    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return service.findTodoById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete  todo matching given id", notes = "Will delete  the todo for the given id")
    public String deleteTodo(@PathVariable Long id) {
        return service.deleteTodoById(id);
    }
}
