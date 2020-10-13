package com.ba.service;

import com.ba.domain.EmailNotification;
import com.ba.domain.Notification;
import com.ba.domain.SmsNotification;
import com.ba.domain.abstraction.BaseEntity;
import com.ba.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public String addNewSmsNotification() {
        SmsNotification notification = new SmsNotification("Ahmet", "YILMAZ", "Java eğitmi başladı", "5555555555");

        notificationRepository.save(notification);

        return notification.toString();
    }

    public String addEmailSmsNotification() {
        EmailNotification notification = new EmailNotification("Can", "YILMAZ", "can@yilmaz.com", "İş garantili eğitim fırsatı");

        notificationRepository.save(notification);

        return notification.toString();
    }

    public List<Notification> findAllNotificationList() {
        return notificationRepository.findAll();
    }

    public Notification findNotificationById(Long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (!optionalNotification.isPresent()) {
            return null;
        }

        return optionalNotification.get();
    }

    public String deleteNotificationById(Long id) {
        notificationRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }
}
