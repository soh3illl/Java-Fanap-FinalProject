package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        bankAccountDAO.createAccount(checkingAccount);
        List<BankAccount> accounts = ORMConfig.getEntityManager().createQuery("SELECT b FROM BankAccount b where accountNumber = 200", BankAccount.class).getResultList();
        Assertions.assertEquals(1,accounts.size());
    }

    @Test
    void findAccountById() {
        BankAccount account = bankAccountDAO.findAccountById(1);
        Assertions.assertEquals(account.getId() , 1);
    }

    @Test
    void deleteAccount() {
        bankAccountDAO.deleteAccount(8);
        BankAccount account = bankAccountDAO.findAccountById(8);
        assertNull(account);
    }

    @Test
    void getAllAccounts() {
        List<BankAccount> allAccounts = bankAccountDAO.getAllAccounts();
        Assertions.assertEquals(7,allAccounts.size());
    }

//    @Test
//    void updateAccount() {
//        bankAccountDAO.updateAccount();
//    }

    @Test
    void filterBalanceByAmount() {
        List<BankAccount> bankAccounts = bankAccountDAO.filterBalanceByAmount(500_000);
        Assertions.assertEquals(4 , bankAccounts.size());
    }
}