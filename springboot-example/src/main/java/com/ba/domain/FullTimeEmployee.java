package com.ba.domain;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

    private int salary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String firstName, String lastName, int salary) {
        super(firstName, lastName);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
