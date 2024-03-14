package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.Transaction;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TransactionDAOTest {
    TransactionDAO transactionDAO;

    @BeforeEach
    void setUp() {
        transactionDAO = new TransactionDAO(ORMConfig.getEntityManager());
    }

    @Test
    void createTransaction() {
        BankAccountDAO bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
        BankAccount account1 = bankAccountDAO.findAccountById(1);
        BankAccount account2 = bankAccountDAO.findAccountById(2);
        Transaction transaction = new Transaction(account1 , account2 , 100.0 );
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.createTransaction(transaction);
        });
    }

    @Test
    void findTransactionById() {
        Transaction transaction = transactionDAO.findTransactionById(1);
        Assertions.assertEquals(1,transaction.getId());
    }

    @Test
    void deleteTransaction() {
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.deleteTransaction(5);
        });
    }

    @Test
    void getAllTransactions() {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        Assertions.assertEquals(3 , transactions.size());

    }

    @Test
    void updateTransaction() {
        Map<String, Object> updates = new HashMap<>();
        BankAccount bankAccount = ORMConfig.getEntityManager().find(BankAccount.class, 4);
        updates.put("fromAccount", bankAccount);
        updates.put("transferAmount", 122_000.0);
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.updateTransaction(updates, 1);
        });
    }

    @Test
    void filterTransactionBasedOnDate() {
        LocalDate dateString = LocalDate.parse("2024-03-14");
        Date date = Date.valueOf(dateString);
        List<Transaction> transactions = transactionDAO.filterTransactionBasedOnDate(date);
        System.out.println(transactions.get(0));
        Assertions.assertEquals(1 , transactions.size());
    }
}