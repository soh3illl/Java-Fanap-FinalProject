package org.example.bank;

import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class Transaction {
    public static void transfer(BankAccount src_account, double amount, BankAccount dest_account) throws InvalidTransactionException {
        src_account.withdraw(amount);
        dest_account.deposit(amount);
    }
}
