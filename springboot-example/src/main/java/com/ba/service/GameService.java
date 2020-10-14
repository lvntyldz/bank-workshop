package com.ba.service;

import com.ba.domain.Game;
import com.ba.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public String addNewGame() {
        Game game = new Game("PES", "A Football Game", 1, 123, 22, false, true, new Date());

        gameRepository.save(game);
        log.info("game added with id : {} successfully.", game.getId());

        return game.toString();
    }

    public List<Game> findAllGameList() {
        return gameRepository.findAll();
    }

    public Game findGameById(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);

        if (!optionalGame.isPresent()) {
            return null;
        }

        return optionalGame.get();
    }

    public String deleteGameById(Long id) {
        gameRepository.deleteById(id);
        log.info("game deleting...id:{}", id);
        return "ID : " + id + " olan içerik silindi";
    }
}
