package com.ba.domain;

import com.ba.domain.abstraction.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Notification extends BaseEntity {

    private String firstName;

    private String lastName;

    private String content;

    public Notification() {
    }

    public Notification(String firstName, String lastName, String content) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
