package org.example.bank;

import org.example.annotations.DeprecatedMethod;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount implements Serializable {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private final Lock lock = new ReentrantLock();
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
        lock.lock();
        try {
            this.balance = balance;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Provide a positive amount");
        }

        this.balance += amount;
    }
    @DeprecatedMethod(reason = "not being synchronized" , replacement = "Deposit")
    public void notSynchronizedDeposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Provide a positive amount");
        }

        this.balance += amount;
    }

    public synchronized void withdraw(double amount) throws InvalidTransactionException {
        if (amount > this.balance || amount <= 0) {
            throw new InsufficientFundsException("This amount could not be withdrawn");
        }

        this.balance -= amount;
    }
    @DeprecatedMethod(reason = "not being synchronized" , replacement = "withdraw")
    public void notSynchronizedWithdraw(double amount) throws InvalidTransactionException {
        if (amount > this.balance || amount <= 0) {
            throw new InsufficientFundsException("This amount could not be withdrawn");
        }

        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}