package org.example;

import org.example.bank.SavingsAccount;

import java.util.ArrayList;
import java.util.List;
import static org.example.controller.SavingsAccountService.applyInterestWithExecutor;

public class TestExecutor {
    public static void main(String[] args) {
        List<SavingsAccount> bankAccounts = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            bankAccounts.add(new SavingsAccount("12344", "bahare"));
            bankAccounts.get(bankAccounts.size()-1).setBalance(50000.0);
        }

        long startTimeWithoutExecutor = System.currentTimeMillis();
        applyInterestWithoutExecutor(bankAccounts);
        long endTimeWithoutExecutor = System.currentTimeMillis();
        long elapsedTimeWithoutExecutor = endTimeWithoutExecutor - startTimeWithoutExecutor;


        resetBalances(bankAccounts);

        long startTimeWithExecutor = System.currentTimeMillis();
        applyInterestWithExecutor(bankAccounts);
        long endTimeWithExecutor = System.currentTimeMillis();
        long elapsedTimeWithExecutor = endTimeWithExecutor - startTimeWithExecutor;

        System.out.println("Time without ExecutorService: " + elapsedTimeWithoutExecutor + " milliseconds");
        System.out.println("Time with ExecutorService: " + elapsedTimeWithExecutor + " milliseconds");
    }
    private static void applyInterestWithoutExecutor(List<SavingsAccount> accounts) {
        System.out.println(accounts.size());
        for (SavingsAccount account : accounts) {
            account.applyInterest();
        }
    }

    private static void resetBalances(List<SavingsAccount> accounts) {
        for (SavingsAccount account : accounts) {
            account.setBalance(500000.0);
        }
    }

}
