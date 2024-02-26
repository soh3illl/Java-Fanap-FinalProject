package org.example;


import org.example.bank.BankAccount;
import org.example.controller.FileAccessor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        FileAccessor.initializer();
        BankAccount bankAccount = new BankAccount("124","mina",19.0);
        BankAccount bankAccount2 = new BankAccount("125","mina",19.0);
        BankAccount bankAccount3 = new BankAccount("124","mina",19.0);
        FileAccessor.serialize(bankAccount);
        FileAccessor.serialize(bankAccount2);
        FileAccessor.serialize(bankAccount3);
        for (BankAccount bank:FileAccessor.deserialize()) {
            System.out.println(bank);
        }
    }
}