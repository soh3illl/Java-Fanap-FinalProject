package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


class BankAccountDAOTest {
    public static BankAccountDAO bankAccountDAO;
    @BeforeEach
    void setUp() {
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }

    @Test
    void createAccount() {
        CheckingAccount checkingAccount = new CheckingAccount("200","sima",50_000.0);
        checkingAccount.setBalance(800_000.0);
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.createAccount(checkingAccount);
        });
    }

    @Test
    void findAccountById() {
        BankAccount account = bankAccountDAO.findAccountById(1);
        Assertions.assertEquals(account.getId() , 1);
    }

    @Test
    void deleteAccount() {
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.deleteAccountById(8);
        });

    }

    @Test
    void getAllAccounts() {
        List<BankAccount> allAccounts = bankAccountDAO.getAllAccounts();
        Assertions.assertEquals(7,allAccounts.size());
    }

    @Test
    void updateAccount() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("overdraftLimit", 10000.0);
        updates.put("accountNumber", "222444");
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.updateAccount(updates, 1);
        });
    }

    @Test
    void filterBalanceByAmount() {
        List<BankAccount> bankAccounts = bankAccountDAO.filterBalanceByAmount(500_000);
        Assertions.assertEquals(4 , bankAccounts.size());
    }
}