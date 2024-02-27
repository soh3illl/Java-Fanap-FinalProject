package org.example;


import org.example.bank.Bank;
import org.example.bank.BankAccount;
import org.example.controller.FileAccessor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Bank bank = new Bank();
        FileAccessor.initializer(bank);
        BankAccount bankAccount = new BankAccount("123456","minaa",19.0);
        BankAccount bankAccount2 = new BankAccount("12345","minaaa",19.0);
        BankAccount bankAccount3 = new BankAccount("1234","minaaaa",19.0);
        FileAccessor.serialize(bankAccount);
        FileAccessor.serialize(bankAccount2);
        FileAccessor.serialize(bankAccount3);
        for (BankAccount bankAccount1:FileAccessor.deserialize().values()) {
            System.out.println(bankAccount1);
        }
    }
}