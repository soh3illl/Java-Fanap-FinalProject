package org.example.controller;

import org.example.bank.SavingsAccount;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SavingsAccountService {
    public static void applyInterestWithExecutor(List<SavingsAccount> accounts) {
        int numberOfThreads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        try {
            for (SavingsAccount account : accounts) {
                executorService.execute(account::applyInterest);
            }
        } finally {
            executorService.shutdown();
        }

    }
}
