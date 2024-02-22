package org.example.bank;

import org.example.core.Validation;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }


    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0);
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (Validation.isNegative(amount)) {
            throw new IllegalArgumentException("Provide a positive amount");
        }

        this.balance += amount;
    }

    public void withdraw(double amount) throws InvalidTransactionException {
        if (Validation.isMoreThanBalance(this.balance, amount) || Validation.isNegative(amount)) {
            throw new InsufficientFundsException("This amount could not be withdrawn");
        }
        this.balance-=amount;
    }
}