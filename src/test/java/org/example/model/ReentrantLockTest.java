package org.example.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReentrantLockTest {
    public static BankAccount savingsAccount1;

    @BeforeAll
    static void setUp() {
        savingsAccount1 = new SavingsAccount("12341", "mahla");
        savingsAccount1.setBalance(500000.0);
    }

    @Test
    void testchangeBalanceOfOneAccountFromDifferentThreads() {
        int count = 0;
//        while (savingsAccount1.getBalance() == 500000.0 && count < 1000) {
        while (true) {

            Thread thread1 = new Thread(() -> {
                savingsAccount1.setBalance(savingsAccount1.getBalance() - 1);
            });
//
            Thread thread2 = new Thread(() -> {
                savingsAccount1.setBalance(savingsAccount1.getBalance() + 1);
            });
            Thread thread3 = new Thread(() -> {
                savingsAccount1.setBalance(savingsAccount1.getBalance() - 2);
            });
            Thread thread4 = new Thread(() -> {
                savingsAccount1.setBalance(savingsAccount1.getBalance() + 2);
            });

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            try {
                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(savingsAccount1.getBalance());
//            count++;
        }
//        Assertions.assertEquals(500000.0, savingsAccount1.getBalance());
    }


}