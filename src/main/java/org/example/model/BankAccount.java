package org.example.model;

import org.example.annotations.DeprecatedMethod;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidTransactionException;

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

    @ManyToOne
    @JoinColumn(name = "accountHolder")
    private AccountHolder accountHolder;

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }
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

    public BankAccount(String accountNumber, AccountHolder accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public BankAccount(String accountNumber, AccountHolder accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Type getType() {
        return this.type;
    }


    public synchronized double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void setBalance(Double balance) {
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

    public static BankAccount createAccountBasedOnType(String accountType) {
        switch (accountType) {
            case "checking":
                return new CheckingAccount();
            case "savings":
                return new SavingsAccount();
            default:
                throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolder=" + accountHolder +
                ", balance=" + balance +
                '}';
    }
}