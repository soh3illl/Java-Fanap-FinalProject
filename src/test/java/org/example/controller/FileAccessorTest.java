package org.example.controller;

import org.example.model.AccountHolder;
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
        AccountHolder accountHolder = new AccountHolder("m.sh","123","mahla","shams",123456L);

        BankAccount bankAccount = new BankAccount("1234567",accountHolder,19.0);
        BankAccount bankAccount2 = new BankAccount("123458",accountHolder,19.0);
        BankAccount bankAccount3 = new BankAccount("12341",accountHolder,19.0);
        FileAccessor.serialize(bankAccount);
        FileAccessor.serialize(bankAccount2);
        FileAccessor.serialize(bankAccount3);
    }
}