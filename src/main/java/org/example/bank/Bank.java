package org.example.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank<T extends BankAccount> {
    private Map<String, T> accounts = new HashMap<>();

    public void addAccount(T account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    public T findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<T> listAccounts() {
        return new ArrayList(accounts.values());
    }

    public void setAccounts(Map<String, T> accounts) {
        this.accounts = accounts;
    }

    public double filterByAmount(double amount) {
        double totalBalance = listAccounts()
                .stream()
                .filter(account -> account.getBalance() > amount)
                .map(account -> account.getBalance())
                .reduce(0.0, (total, balance) -> total += balance);

        return totalBalance;
    }

    public void applyInterest() {
        listAccounts()
                .stream()
                .filter(account -> account.getType() == BankAccount.Type.SAVING)
                .map(account -> (SavingsAccount) account)
                .forEach(account -> account.applyInterest());
    }
}