package org.example.controller;

import org.example.bank.SavingsAccount;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SavingsAccountService {
    public static void applyInterestWithExecutor(List<SavingsAccount> accounts) {
        int numberOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Task1 task1 = new Task1(accounts);
        Task2 task2 = new Task2(accounts);
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}


class Task1 implements Runnable {
    List<SavingsAccount> bankAccount;
    public Task1(List<SavingsAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
    public void run() {
        for (int i = 0; i <= bankAccount.size() / 2; i++) {
            bankAccount.get(i).applyInterest();
        }
    }
}
class Task2 implements Runnable {
    List<SavingsAccount> bankAccount;
    public Task2(List<SavingsAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
    public void run() {
        for (int i = (bankAccount.size() / 2) + 1; i < bankAccount.size(); i++) {
            bankAccount.get(i).applyInterest();
        }
    }
}
