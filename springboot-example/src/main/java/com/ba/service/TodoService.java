package com.ba.service;

import com.ba.domain.Todo;
import com.ba.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public String addNewTodo() {
        Todo todo = new Todo("Market", "makarna alınacak");

        todoRepository.save(todo);

        return todo.toString();
    }

    public List<Todo> findAllTodoList() {
        return todoRepository.findAll();
    }

    public Todo findTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            return null;
        }

        return optionalTodo.get();
    }

    public String deleteTodoById(Long id) {
        todoRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }
}
