package org.example.core;

public class Validation {
    public static boolean isNegative(double withdrawalAmount) {
        return withdrawalAmount < 0;
    }

    public static boolean isMoreThanBalance(double withdrawalAmount, double balance) {
        return withdrawalAmount > balance;
    }
    public static boolean isHigherThanOverdraftLimit(double withdrawalAmount , double balance , double overdraftLimit){
        return (withdrawalAmount - balance) > overdraftLimit;
    }
    public static boolean isLessThanMinimumBalance(double withdrawalAmount, double balance, double minimumBalance){
        return (balance - withdrawalAmount) < minimumBalance;
    }
}