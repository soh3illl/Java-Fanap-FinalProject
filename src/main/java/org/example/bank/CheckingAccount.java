package org.example.bank;

import org.example.annotations.DeprecatedMethod;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class CheckingAccount extends BankAccount {
    private Double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    public CheckingAccount(String accountNumber, String accountHolderName, Double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    public Double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(Double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amount) throws InvalidTransactionException {
        double fee = calculateFees(amount);
        double withdrawalAmount = amount + fee;

        if (amount <= 0) {
            throw new InsufficientFundsException("The entered amount is negative");
        }

        if (this.getBalance() + this.getOverdraftLimit() < withdrawalAmount) {
            throw new InsufficientFundsException("This amount could not be withdrawn because it is more than overdraftLimit");
        }

        this.setBalance(this.getBalance() - amount);
        deductFees(amount);
    }
    @DeprecatedMethod(reason = "not calculating fees and not being synchronized",replacement = "withdraw")
    public void withdrawWithoutFees(double amount) {

        if (amount <= 0) {
            throw new InsufficientFundsException("The entered amount is negative");
        }

        if (this.getBalance() + this.getOverdraftLimit() < amount) {
            throw new InsufficientFundsException("This amount could not be withdrawn because it is more than overdraftLimit");
        }

        this.setBalance(this.getBalance() - amount);
    }

    public void deductFees(double amount) {
        double fee = calculateFees(amount);
        this.setBalance(this.getBalance() - fee);
    }

    public static double calculateFees(double amount) {
        if (amount < 10_000) {
            return 300.0;
        }

        if (amount >= 10_000 && amount < 100_000) {
            return 900.0;
        }

        if (amount >= 100_000 && amount < 1_000_000) {
            return 1000.0;
        }

        if (amount >= 1_000_000 && amount < 10_000_000) {
            return 1500.0;
        }

        if (amount >= 10_000_000 && amount < 100_000_000) {
            return 2400.0;
        }

        if (amount >= 100_000_000 && amount < 1_000_000_000) {
            return 3500.0;
        }

        return 5000.0;
    }
}
