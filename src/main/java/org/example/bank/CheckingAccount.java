package org.example.bank;

import org.example.core.Validation;
import org.example.core.exception.InsufficientFundsException;
import org.example.core.exception.InvalidTransactionException;

public class CheckingAccount extends BankAccount{
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
    public void withdraw(double amount) throws InvalidTransactionException {
        if (Validation.isHigherThanOverdraftLimit(amount , this.getBalance() , this.overdraftLimit)){
            throw new InsufficientFundsException("This amount could not be withdrawn because it is more than overdraftLimit");
        }
        if (Validation.isNegative(amount)){
            throw new InsufficientFundsException("The entered amount is negative");
        }
        this.setBalance(this.getBalance() - amount);
        deductFees(amount);
    }

    public void deductFees(double amount){
        TransferAmountPerRange transferAmountPerRange = getAmountRange(amount);
        this.setBalance(this.getBalance()-transferAmountPerRange.value);
    }

    public static TransferAmountPerRange getAmountRange(double amount){
        if (amount < 1000000.0) {
            return TransferAmountPerRange.lessThan1Million;
        }
        if (amount>1000000.0 && amount < 2000000.0){
            return TransferAmountPerRange.between1and2Million;
        }
        if (amount>2000000.0 && amount < 3000000.0){
            return TransferAmountPerRange.between2and3Million;
        }
        if (amount>3000000.0 && amount < 4000000.0){
            return TransferAmountPerRange.between3and4Million;
        }
        if (amount>4000000.0 && amount < 5000000.0){
            return TransferAmountPerRange.between4and5Million;
        }
        if (amount>5000000.0 && amount < 6000000.0){
            return TransferAmountPerRange.between5and6Million;
        }
        if (amount>6000000.0 && amount < 7000000.0){
            return TransferAmountPerRange.between6and7Million;
        }
        if (amount>7000000.0 && amount < 8000000.0){
            return TransferAmountPerRange.between7and8Million;
        }
        if (amount>8000000.0 && amount < 9000000.0){
            return TransferAmountPerRange.between8and9Million;
        }
        if (amount>9000000.0 && amount < 10000000.0){
            return TransferAmountPerRange.between9and10Million;
        }
        if (amount>10000000.0 && amount < 11000000.0){
            return TransferAmountPerRange.between10and11Million;
        }
        if (amount>11000000.0 && amount < 12000000.0){
            return TransferAmountPerRange.between11and12Million;
        }
        if (amount>12000000.0 && amount < 13000000.0) {
            return TransferAmountPerRange.between12and13Million;
        }
        if (amount>13000000.0 && amount < 14000000.0){
            return TransferAmountPerRange.between13and14Million;
        }
        return TransferAmountPerRange.between14and15Million;

    }
}

