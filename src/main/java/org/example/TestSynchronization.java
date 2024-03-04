package org.example;

import org.example.bank.BankAccount;
import org.example.bank.SavingsAccount;
import org.example.core.exception.InvalidTransactionException;

import java.util.ArrayList;
import java.util.List;

public class TestSynchronization {
    public static void main(String[] args) {
        SavingsAccount savingsAccount1 = new SavingsAccount("12341", "mahla");
        savingsAccount1.setBalance(500000.0);

        SavingsAccount savingsAccount2 = new SavingsAccount("12342", "mahsa");
        savingsAccount2.setBalance(500000.0);

        SavingsAccount savingsAccount3 = new SavingsAccount("12343", "soheil");
        savingsAccount3.setBalance(500000.0);

        SavingsAccount savingsAccount4 = new SavingsAccount("12344", "bahare");
        savingsAccount4.setBalance(500000.0);
        List<SavingsAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(savingsAccount1);
        bankAccounts.add(savingsAccount2);
        bankAccounts.add(savingsAccount3);
        bankAccounts.add(savingsAccount4);
        while (true) {
            Thread thread1 = new Thread(() -> {
                try {
                    savingsAccount1.withdraw(1);
                } catch (InvalidTransactionException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            });
            Thread thread5 = new Thread(() -> {
                try {
                    savingsAccount3.withdraw(5);
                } catch (InvalidTransactionException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            });
            Thread thread6 = new Thread(() -> {
                savingsAccount4.deposit(5);
            });
            Thread thread2 = new Thread(() -> {
                savingsAccount2.deposit(1);
            });

            Thread thread3 = new Thread(() -> {
                try {
                    savingsAccount2.withdraw(2);
                } catch (InvalidTransactionException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            });
            Thread thread4 = new Thread(() -> {
                savingsAccount1.deposit(2);
            });

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();

            try {
                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
                thread5.join();
                thread6.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double j = 0.0;
            for (BankAccount i:bankAccounts) {
                j += i.getBalance();
            }
            System.out.println(j);
        }
    }
}
