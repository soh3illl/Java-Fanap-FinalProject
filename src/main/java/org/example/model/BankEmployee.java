package org.example.model;

import javax.persistence.Entity;

@Entity
public class BankEmployee extends User{
    private int employeeId;

    public BankEmployee(String username, String password, String firstName, String lastName, Long nationalCode, int employeeId) {
        super(username, password, firstName, lastName, nationalCode);
        this.employeeId = employeeId;
    }

    public BankEmployee() {
    }

    public BankEmployee(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
