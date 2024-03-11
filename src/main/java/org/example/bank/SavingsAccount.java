package org.example.bank;

import org.example.annotations.DeprecatedMethod;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class SavingsAccount extends BankAccount {
    private final double interestRate = 0.2;
    private final double minimumBalance = 10_000;

    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
        this.type = Type.SAVING;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interestAmount = this.getBalance() * this.interestRate;
        this.setBalance(this.getBalance() + interestAmount);
    }

    @Override
    public synchronized void withdraw(double amount) throws InvalidTransactionException {
        if ((this.getBalance() - amount) < this.getMinimumBalance()) {
            throw new InvalidTransactionException("This amount could not be withdrawn because it is more than minimum balance");
        }

        super.withdraw(amount);
    }

    @DeprecatedMethod(reason = "not being synchronized" , replacement = "withdraw")
    public void notSynchronizedWithdraw(double amount) throws InvalidTransactionException {
        if ((this.getBalance() - amount) < this.getMinimumBalance()) {
            throw new InvalidTransactionException("This amount could not be withdrawn because it is more than minimum balance");
        }

        super.withdraw(amount);
    }
}
