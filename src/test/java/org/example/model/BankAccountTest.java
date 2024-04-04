package org.example.model;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidTransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BankAccountTest {
    public static BankAccount bankAccount;
    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("123456", new AccountHolder("m.sh","123",
                "mahla","shams", 123456L), 1000.0);
    }
    @Test
    void givenValidDepositAmount_whenDepositing_thenBalanceIncreases() {
        double initialBalance = bankAccount.getBalance();
        bankAccount.deposit(500.0);
        Assertions.assertEquals(initialBalance + 500.0, bankAccount.getBalance());
    }

    @Test
    void givenNegativeDepositAmount_whenDepositing_thenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.deposit(-500.0);
        });
    }

    @Test
    void givenValidWithdrawalAmount_whenWithdrawing_thenBalanceDecreases() throws InvalidTransactionException {
        double initialBalance = bankAccount.getBalance();
        bankAccount.withdraw(500.0);
        Assertions.assertEquals(initialBalance - 500.0, bankAccount.getBalance());
    }

    @Test
    void givenNegativeWithdrawalAmount_whenWithdrawing_thenThrowsInsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            bankAccount.withdraw(-500.0);
        });
    }

    @Test
    void givenInsufficientFunds_whenWithdrawing_thenThrowsInsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            bankAccount.withdraw(1500.0);
        });
    }

}