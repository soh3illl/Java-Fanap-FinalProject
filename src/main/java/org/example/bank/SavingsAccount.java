package org.example.bank;

import org.example.core.Validation;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class SavingsAccount extends BankAccount{
    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    public SavingsAccount(String accountNumber, String accountHolderName, double interestRate, double minimumBalance) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void applyInterest(){
        double interestAmount = this.getBalance() * this.interestRate;
        this.setBalance(this.getBalance() + interestAmount);
    }

    @Override
    public void withdraw(double amount) throws InvalidTransactionException {
        if (Validation.isLessThanMinimumBalance(this.getBalance(), amount,this.minimumBalance)) {
            throw new InvalidTransactionException("This amount could not be withdrawn because it is more than minimum balance");
        }
        if (Validation.isNegative(amount)){
            throw new InsufficientFundsException("The entered amount is negative");
        }
        this.setBalance(this.getBalance() - amount);
    }
}
