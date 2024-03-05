package org.example.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<BankAccount> listAccounts() {
        return new ArrayList(accounts.values());
    }

    public void setAccounts(Map<String, BankAccount> accounts) {
        this.accounts = accounts;
    }
}