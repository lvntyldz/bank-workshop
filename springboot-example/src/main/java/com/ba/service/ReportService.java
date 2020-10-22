package com.ba.service;

import com.ba.domain.Game;
import com.ba.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportService {

    public static final String TODO_FILE_PATH = "./target/todoList.pdf";
    public static final String GAME_FILE_PATH = "./target/gameList.pdf";

    @Autowired
    private TodoService todoService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PdfService pdfService;

    @Async("processExecutor")
    public void generateTodoReports() throws Exception {
        log.info("Received request to generate TODO Report");

        for (int i = 0; i < 100; i++) {
            todoService.addNewTodo();
        }

        List<Todo> todos = todoService.findAllTodoList();

        pdfService.generateTodoList(TODO_FILE_PATH, todos);

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException ie) {
            log.error("Error in Export TodoList: {}", ie.getMessage());
        }

        log.info("TODO report generate operation completed");
    }

    @Async("processExecutor")
    public void generateGameReports() throws Exception {
        log.info("Received request to generate Game Report");

        for (int i = 0; i < 100; i++) {
            gameService.addNewGame();
        }

        List<Game> games = gameService.findAllGameList();

        pdfService.generateGameList(GAME_FILE_PATH, games);

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException ie) {
            log.error("Error in Export GameList: {}", ie.getMessage());
        }

        log.info("GAME report generate operation completed");
    }
}
