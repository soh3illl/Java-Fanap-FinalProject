package org.example.model;

import org.example.model.AccountHolder;
import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.DAOs.BankAccountDAO;
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
        ORMConfig.init();
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }

    @Test
    void givenAccountDetails_whenCreatingAccount_thenAccountCreateSuccessfully() {
        AccountHolder accountHolder = new AccountHolder("m.sh", "123", "mahla", "shams", 123456L);
        CheckingAccount checkingAccount = new CheckingAccount("210", accountHolder, 51_000.0);
        checkingAccount.setBalance(800_000.0);
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.createAccount(checkingAccount);
        });
    }

    @Test
    void givenAccountId_whenFindingAccountById_thenAccountFound() {
        BankAccount account = bankAccountDAO.findAccountById(1);
        Assertions.assertEquals(account.getId(), 1);
    }

    @Test
    void givenAccountId_whenDeletingAccount_thenAccountDeleteSuccessfully() {
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.deleteAccountById(8);
        });
    }

    @Test
    void whenGettingAllAccounts_thenCorrectNumberOfAccountsReturned() {
        List<BankAccount> allAccounts = bankAccountDAO.getAllAccounts();
        Assertions.assertEquals(7, allAccounts.size());
    }

    @Test
    void givenAccountUpdates_whenUpdatingAccount_thenAccountUpdateSuccessfully() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("overdraftLimit", 10000.0);
        updates.put("accountNumber", "222444");
        Assertions.assertDoesNotThrow(() -> {
            bankAccountDAO.updateAccount(updates, 1);
        });
    }

    @Test
    void givenBalanceAmount_whenFilteringAccountsByBalance_thenCorrectNumberOfAccountsReturned() {
        List<BankAccount> bankAccounts = bankAccountDAO.filterBalanceByAmount(500_000);
        Assertions.assertEquals(4, bankAccounts.size());
    }
}
