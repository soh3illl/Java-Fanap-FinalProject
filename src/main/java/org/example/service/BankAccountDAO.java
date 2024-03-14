package org.example.service;

import org.example.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BankAccountDAO {

    private EntityManager entityManager;
    public BankAccountDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void createAccount(BankAccount bankAccount) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(bankAccount);
        transaction.commit();
    }

    public BankAccount findAccountById(int accountId) {
        return entityManager.find(BankAccount.class, accountId);
    }

    public void deleteAccount(int accountId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        BankAccount bankAccount = entityManager.find(BankAccount.class, accountId);
        if (bankAccount != null) {
            entityManager.remove(bankAccount);
        }
        transaction.commit();
    }

    public List<BankAccount> getAllAccounts() {
        return entityManager.createQuery("SELECT b FROM BankAccount b", BankAccount.class).getResultList();
    }

//    public void updateAccount(BankAccount newAccount , Integer id) {
//        EntityTransaction transaction = this.getEntityManager().getTransaction();
//        transaction.begin();
//        BankAccount updatedAccount = this.getEntityManager().find(BankAccount.class, id);
//        updatedAccount.setBalance(newAccount.getBalance());
//        updatedAccount.setAccountNumber(newAccount.getAccountNumber());
//        updatedAccount.setAccountHolderName(newAccount.getAccountHolderName());
//        transaction.commit();
//    }

    public List<BankAccount> filterBalanceByAmount(double balance) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteriaQuery = criteriaBuilder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteriaQuery.from(BankAccount.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.greaterThan(root.get("balance"), balance));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

//    public static void main(String[] args) {
////        TransactionDAO transactionDAO = new TransactionDAO(ORMConfig.getEntityManager());
//        BankAccountDAO checkingAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
//        CheckingAccount checkingAccount1 = new CheckingAccount("1238","zahra",50_000.0);
//        checkingAccount1.setBalance(800_000.0);
//        CheckingAccount checkingAccount2 = new CheckingAccount("1239","soheil",50_000.0);
//        checkingAccount2.setBalance(900_000.0);
//        SavingsAccount savingsAccount = new SavingsAccount("12342","reza");
//        savingsAccount.setBalance(500_000);
////        checkingAccountDAO.createAccount(checkingAccount1);
////        checkingAccountDAO.createAccount(checkingAccount2);
////        checkingAccountDAO.createAccount(savingsAccount);
////        checkingAccountDAO.createCheckingAccount(savingsAccount);
////        checkingAccountDAO.updateCheckingAccount(checkingAccount2 , 1);
////        for (BankAccount b: checkingAccountDAO.findAccountsByBalanceGreaterThan(500_000)) {
////            System.out.println(b);
////        }
//    }
}
