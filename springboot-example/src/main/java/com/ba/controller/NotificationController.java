package com.ba.controller;

import com.ba.domain.Notification;
import com.ba.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("/sms/add")
    public String addSmsNotification() {
        return service.addNewSmsNotification();
    }

    @GetMapping("/email/add")
    public String addEmailNotification() {
        return service.addEmailSmsNotification();
    }


    @GetMapping("/list")
    public List<Notification> getAllNotification() {
        return service.findAllNotificationList();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return service.findNotificationById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id) {
        return service.deleteNotificationById(id);
    }
}
