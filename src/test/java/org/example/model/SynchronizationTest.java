package org.example.model;

import org.example.exception.InvalidTransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SynchronizationTest {
    public static BankAccount savingsAccount1;
    public static AccountHolder accountHolder;
    @BeforeAll
    static void setUp() {
        accountHolder = new AccountHolder("m.sh","123","mahla","shams",123456L);

        savingsAccount1 = new SavingsAccount("12341", accountHolder);
        savingsAccount1.setBalance(500000.0);
    }
    @Test
    void testTransferFromOneAccountFromTowDifferentThreads() {
        int count =0 ;
        while (savingsAccount1.getBalance() == 500000.0 && count < 1000) {
            Thread thread1 = new Thread(() -> {
                try {
                    savingsAccount1.withdraw(1);
                } catch (InvalidTransactionException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            });
//
            Thread thread2 = new Thread(() -> {
                savingsAccount1.deposit(1);
            });

            thread1.start();
            thread2.start();


            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(savingsAccount1.getBalance());
            count++;
        }
        Assertions.assertEquals(500000.0,savingsAccount1.getBalance());
    }
}