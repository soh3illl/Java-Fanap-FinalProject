package org.example.controller;

import org.example.model.Bank;
import org.example.model.BankAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileAccessorTest {

    @BeforeAll
    public static void setUp() {
        Bank bank = new Bank();
        FileAccessor.initializer(bank);
        for (Object bankAccount: bank.listAccounts()) {
            System.out.println(bankAccount);
        }
    }

    @Test
    void deserialize() {
        for (BankAccount bankAccount1:FileAccessor.deserialize().values()) {
            System.out.println(bankAccount1);
        }
    }

    @Test
    void serialize() {
        BankAccount bankAccount = new BankAccount("1234567","minaa",19.0);
        BankAccount bankAccount2 = new BankAccount("123458","minaaa",19.0);
        BankAccount bankAccount3 = new BankAccount("12341","minaaaa",19.0);
        FileAccessor.serialize(bankAccount);
        FileAccessor.serialize(bankAccount2);
        FileAccessor.serialize(bankAccount3);
    }
}