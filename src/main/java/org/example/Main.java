package org.example;

import org.example.bank.BankAccount;
import org.example.bank.Transaction;
import org.example.core.db.DB;
import org.example.core.exception.InvalidTransactionException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidTransactionException {
        BankAccount src_account = new BankAccount("123", "soheil", 800);
        BankAccount des_account = new BankAccount("456", "mahla", 200);

        Transaction.transfer(src_account, 200, des_account);

        System.out.println(src_account.getBalance());
        System.out.println(des_account.getBalance());

        DB db = new DB();
//        db.insert(List.of("src_account", "amount", "dest_account"), List.of("123", "800", "456"));

    }
}