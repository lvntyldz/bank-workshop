package com.ba.domain;

import javax.persistence.Entity;

@Entity
public class SmsNotification extends Notification {

    private String phoneNumber;

    public SmsNotification() {
    }

    public SmsNotification(String firstName, String lastName, String content, String phoneNumber) {
        super(firstName, lastName, content);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
