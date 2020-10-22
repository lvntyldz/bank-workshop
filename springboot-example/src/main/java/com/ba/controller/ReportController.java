package com.ba.controller;

import com.ba.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("export-report")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/todo")
    public String exportTodoReport() throws Exception {
        service.generateTodoReports();
        return "Todo raporu oluşturma isteği alındı";
    }

    @GetMapping("/game")
    public String exportGameReport() throws Exception {
        service.generateGameReports();
        return "Game raporu oluşturma isteği alındı";
    }

}
