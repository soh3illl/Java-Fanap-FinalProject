package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.Transaction;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.valueOf(localDate);
        Transaction transaction = new Transaction(account1 , account2 , 10.0 , currentDate);
        transactionDAO.createTransaction(transaction);
//        List<BankAccount> accounts = ORMConfig.getEntityManager().createQuery(
//                "SELECT t FROM Transaction t where fromAccount = account1.id and toAccount = account2.id and date = currentDate"
//                , BankAccount.class).getResultList();
//        Assertions.assertEquals(1,accounts.size());
    }

    @Test
    void findTransactionById() {
    }

    @Test
    void deleteTransaction() {
    }

    @Test
    void getAllTransactions() {
    }

    @Test
    void updateTransaction() {
    }

    @Test
    void filterTransactionBasedOnDate() {
    }
}