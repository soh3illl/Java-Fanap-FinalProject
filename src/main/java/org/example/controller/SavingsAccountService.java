package org.example.controller;

import org.example.bank.SavingsAccount;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SavingsAccountService {
    public static void applyInterestWithExecutor(List<SavingsAccount> accounts) {
        int numberOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= accounts.size() / 2; i++) {
                accounts.get(i).applyInterest();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = (accounts.size() / 2) + 1; i < accounts.size(); i++) {
                accounts.get(i).applyInterest();
            }
        });
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void applyInterestWithoutExecutor(List<SavingsAccount> accounts) {
        for (SavingsAccount account : accounts) {
            account.applyInterest();
        }
    }
}
