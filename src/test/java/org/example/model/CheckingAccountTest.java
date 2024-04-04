package org.example.model;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidTransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    public static CheckingAccount checkingAccount;
    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount("123456", new AccountHolder("m.sh","123",
                "mahla","shams", 123456L),1000.0);
    }
    @Test
    void givenValidDepositAmount_whenDepositing_thenBalanceIncreases() {
        double initialBalance = checkingAccount.getBalance();
        checkingAccount.deposit(500.0);
        Assertions.assertEquals(initialBalance + 500.0, checkingAccount.getBalance());
    }

    @Test
    void givenNegativeDepositAmount_whenDepositing_thenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkingAccount.deposit(-500.0);
        });
    }

    @Test
    void givenValidWithdrawalAmount_whenWithdrawing_thenBalanceDecreases() throws InvalidTransactionException {
        double initialBalance = checkingAccount.getBalance();
        checkingAccount.withdraw(500.0);
        Assertions.assertEquals(initialBalance - 500.0 - 300.0, checkingAccount.getBalance());
    }

    @Test
    void givenNegativeWithdrawalAmount_whenWithdrawing_thenThrowsInsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            checkingAccount.withdraw(-500.0);
        });
    }

    @Test
    void givenInsufficientFunds_whenWithdrawing_thenThrowsInsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            checkingAccount.withdraw(15000.0);
        });
    }
}