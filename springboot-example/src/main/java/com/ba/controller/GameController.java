package com.ba.controller;

import com.ba.domain.Game;
import com.ba.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping("/add")
    public String addGame() {
        return service.addNewGame();
    }

    @GetMapping("/list")
    public List<Game> getAllGame() {
        return service.findAllGameList();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return service.findGameById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        return service.deleteGameById(id);
    }
}
