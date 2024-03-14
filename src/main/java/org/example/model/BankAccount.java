package org.example.model;

import org.example.annotations.DeprecatedMethod;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BankAccount implements Serializable {
    public BankAccount() {

    }

    public enum Type {BASE, CHECKING, SAVING}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    @Transient
    private final Lock lock = new ReentrantLock();
    @Transient
    protected Type type = Type.BASE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public BankAccount(Integer id, String accountNumber, String accountHolderName, double balance) {
        this.id = id;
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

    public Type getType() {
        return this.type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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