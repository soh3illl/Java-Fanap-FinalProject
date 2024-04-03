package org.example.model;

import org.example.exception.InvalidTransactionException;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private BankAccount fromAccount;
    @OneToOne
    @JoinColumn(name = "to_account_id")
    private BankAccount toAccount;
    private Double transferAmount;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction(BankAccount fromAccount, BankAccount toAccount, Double transferAmount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferAmount = transferAmount;
        this.date = new Date(System.currentTimeMillis());
    }

    public Transaction() {
    }

    public Transaction(BankAccount fromAccount, BankAccount toAccount, Double transferAmount, Date date) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferAmount = transferAmount;
        this.date = date;
    }

    public BankAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(BankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public BankAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(BankAccount toAccount) {
        this.toAccount = toAccount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }
    public void transfer(BankAccount toAccount , BankAccount fromAccount , Double amount){
        try {
            fromAccount.withdraw(amount);
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
            return;
        }
        toAccount.deposit(amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", transferAmount=" + transferAmount +
                ", date=" + date +
                '}';
    }
}
