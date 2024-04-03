package org.example.model;

import org.example.model.BankAccount;
import org.example.model.DAOs.BankAccountDAO;
import org.example.model.DAOs.TransactionDAO;
import org.example.model.Transaction;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TransactionDAOTest {
    TransactionDAO transactionDAO;

    @BeforeEach
    void setUp() {
        ORMConfig.init();
        transactionDAO = new TransactionDAO(ORMConfig.getEntityManager());
    }

    @Test
    void givenTransactionDetails_whenCreatingTransaction_thenTransactionCreateSuccessfully() {
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.createTransaction(7, 8, 1000.0);
        });
    }

    @Test
    void givenTransactionId_whenFindingTransactionById_thenTransactionFound() {
        Transaction transaction = transactionDAO.findTransactionById(1);
        Assertions.assertEquals(1, transaction.getId());
    }

    @Test
    void givenTransactionId_whenDeletingTransaction_thenTransactionDeleteSuccessfully() {
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.deleteTransaction(5);
        });
    }

    @Test
    void whenGettingAllTransactions_thenCorrectNumberOfTransactionsReturned() {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        Assertions.assertEquals(3, transactions.size());
    }

    @Test
    void givenTransactionUpdates_whenUpdatingTransaction_thenTransactionUpdateSuccessfully() {
        Map<String, Object> updates = new HashMap<>();
        BankAccount bankAccount = ORMConfig.getEntityManager().find(BankAccount.class, 4);
        updates.put("fromAccount", bankAccount);
        updates.put("transferAmount", 122_000.0);
        Assertions.assertDoesNotThrow(() -> {
            transactionDAO.updateTransaction(updates, 1);
        });
    }

    @Test
    void givenDate_whenFilteringTransactions_thenCorrectNumberOfTransactionsReturned() {
        LocalDate dateString = LocalDate.parse("2024-03-14");
        Date date = Date.valueOf(dateString);
        List<Transaction> transactions = transactionDAO.filterTransactionBasedOnDate(date);
        Assertions.assertEquals(1, transactions.size());
    }
}
