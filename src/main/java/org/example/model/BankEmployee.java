package org.example.model;

import javax.persistence.Entity;

@Entity
public class BankEmployee extends User{
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
