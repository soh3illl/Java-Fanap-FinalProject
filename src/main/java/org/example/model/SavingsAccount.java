package org.example.model;

import org.example.annotations.DeprecatedMethod;
import org.example.exception.InvalidTransactionException;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class SavingsAccount extends BankAccount {
    private final double interestRate = 0.2;
    private final double minimumBalance = 10_000;
    @Transient
    protected Type type = Type.SAVING;

    public SavingsAccount(String accountNumber, AccountHolder accountHolder) {
        super(accountNumber, accountHolder);
        this.type = Type.SAVING;
    }

    public SavingsAccount(String accountNumber, AccountHolder accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
        this.type = Type.SAVING;
    }

    @Override
    public Type getType() {
        return type;
    }

    public SavingsAccount() {

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

    @Override
    public String toString() {
        return super.toString()+" SavingsAccount{" +
                "interestRate=" + interestRate +
                ", minimumBalance=" + minimumBalance +
                "} ";
    }
}
