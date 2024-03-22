package org.example.service;

import org.example.model.BankEmployee;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    public static UserDAO userDAO;

    @BeforeEach
    void setUp() {
        ORMConfig.init();
        userDAO= new UserDAO(ORMConfig.getEntityManager());
    }

    @Test
    void createUserAccount() {
        BankEmployee bankEmployee = new BankEmployee();
        bankEmployee.setFirstName("reza");
        bankEmployee.setLastName("farzin");
        bankEmployee.setUsername("r.f");
        bankEmployee.setPassword("1234");
        bankEmployee.setEmployeeId(1);
        userDAO.createUserAccount(bankEmployee);
    }

    @Test
    void findUserById() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getAllAccountHolders() {
    }

    @Test
    void getAllBankEmployee() {
    }

    @Test
    void getUserByUsernameAndPassword() {
    }
}