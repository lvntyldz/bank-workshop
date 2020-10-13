package com.ba.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Payment implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
