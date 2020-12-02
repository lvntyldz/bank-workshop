package com.ba.domain;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

    private int hourlyRate;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String firstName, String lastName, int hourlyRate) {
        super(firstName, lastName);
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "firstName=" + getFirstName() +","+
                "lastName=" + getLastName() +
                '}';
    }
}
