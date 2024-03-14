package org.example.service;

import org.example.utils.ORMConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountDAOTest {
    public static BankAccountDAO bankAccountDAO;
    @BeforeEach
    void setUp() {
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }

    @Test
    void updateCheckingAccount() {
    }
}