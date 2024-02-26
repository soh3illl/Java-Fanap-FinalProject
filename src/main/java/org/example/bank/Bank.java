package org.example.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private static Map<String, BankAccount> accounts = new HashMap<>();

    public static void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public static void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    public static BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public static List listAccounts() {
        return new ArrayList(accounts.values());
    }
}