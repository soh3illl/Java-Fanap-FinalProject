package org.example.model;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidTransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SavingsAccountTest {

    public static SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount("123456", new AccountHolder("m.sh","123",
                "mahla","shams", 123456L),100000.0);
    }
    @Test
    void givenValidWithdrawalAmount_whenWithdrawing_thenBalanceDecreases() throws InvalidTransactionException {
        double initialBalance = savingsAccount.getBalance();
        savingsAccount.withdraw(500.0);
        Assertions.assertEquals(initialBalance - 500.0, savingsAccount.getBalance());
    }

    @Test
    void givenNegativeWithdrawalAmount_whenWithdrawing_thenThrowsInsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            savingsAccount.withdraw(-500.0);
        });
    }

    @Test
    void givenInsufficientFunds_whenWithdrawing_thenThrowsInvalidTransactionException() {
        Assertions.assertThrows(InvalidTransactionException.class, () -> {
            savingsAccount.withdraw(95000.0);
        });
    }

    @Test
    void givenValidDepositAmount_whenDepositing_thenBalanceIncreases() {
        double initialBalance = savingsAccount.getBalance();
        savingsAccount.deposit(500.0);
        Assertions.assertEquals(initialBalance + 500.0, savingsAccount.getBalance());
    }

    @Test
    void givenNegativeDepositAmount_whenDepositing_thenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.deposit(-500.0);
        });
    }
}