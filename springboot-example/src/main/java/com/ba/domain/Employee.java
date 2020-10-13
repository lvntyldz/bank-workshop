package com.ba.domain;

import com.ba.domain.abstraction.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
//@DiscriminatorColumn(name = "EMP_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Employee extends BaseEntity {

    private String firstName;

    private String lastName;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
