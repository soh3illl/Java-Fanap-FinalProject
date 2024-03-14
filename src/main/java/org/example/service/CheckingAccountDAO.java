package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;
import org.example.utils.ORMConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CheckingAccountDAO extends BankAccountDAO {
    public CheckingAccountDAO(EntityManager entityManager) throws ClassNotFoundException {
        super(entityManager);
    }


    public void updateCheckingAccount(CheckingAccount newAccount, Integer id) {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        CheckingAccount updatedAccount = this.getEntityManager().find(CheckingAccount.class, id);
        updatedAccount.setBalance(newAccount.getBalance());
        updatedAccount.setAccountNumber(newAccount.getAccountNumber());
        updatedAccount.setAccountHolderName(newAccount.getAccountHolderName());
        updatedAccount.setOverdraftLimit(newAccount.getOverdraftLimit());
        transaction.commit();
    }

}
