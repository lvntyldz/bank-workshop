package com.ba.domain;

import javax.persistence.Entity;

@Entity
public class EmailNotification extends Notification {

    private String email;

    public EmailNotification() {
    }

    public EmailNotification(String firstName, String lastName, String content, String email) {
        super(firstName, lastName, content);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
