package org.example.service;

import org.example.model.AccountHolder;
import org.example.model.SavingsAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.service.SavingsAccountService.applyInterestWithExecutor;
import static org.example.service.SavingsAccountService.applyInterestWithoutExecutor;

class SavingsAccountServiceTest {
    public static List<SavingsAccount> bankAccounts;

    @BeforeAll
    static void setUp() {
        bankAccounts = new ArrayList<>();
        AccountHolder accountHolder = new AccountHolder("m.sh","123","mahla","shams",123456L);
        for (int i = 0; i < 10_000_000; i++) {
            bankAccounts.add(new SavingsAccount("12344", accountHolder));
            bankAccounts.get(bankAccounts.size()-1).setBalance(50000.0);
        }
    }

    @Test
    void testCompareApplyInterestWithExecutorAndWithoutExecutor() {
        long startTimeWithoutExecutor = System.currentTimeMillis();
        applyInterestWithoutExecutor(bankAccounts);
        long endTimeWithoutExecutor = System.currentTimeMillis();
        long spendTimeWithoutExecutor = endTimeWithoutExecutor - startTimeWithoutExecutor;

        resetBalances(bankAccounts);

        long startTimeWithExecutor = System.currentTimeMillis();
        applyInterestWithExecutor(bankAccounts);
        long endTimeWithExecutor = System.currentTimeMillis();
        long spendTimeWithExecutor = endTimeWithExecutor - startTimeWithExecutor;

        System.out.println("Time without ExecutorService: " + spendTimeWithoutExecutor + " milliseconds");
        System.out.println("Time with ExecutorService: " + spendTimeWithExecutor + " milliseconds");
        Assertions.assertTrue(spendTimeWithExecutor < spendTimeWithoutExecutor);
    }


    public static void resetBalances(List<SavingsAccount> accounts) {
        for (SavingsAccount account : accounts) {
            account.setBalance(50000.0);
        }
    }

}