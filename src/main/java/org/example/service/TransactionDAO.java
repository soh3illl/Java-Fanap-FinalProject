package org.example.service;

import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;
import org.example.model.Transaction;
import org.example.utils.ORMConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TransactionDAO {
    private EntityManager entityManager;

    public TransactionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    static {
        System.out.println("hi");
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void createTransaction(Transaction bankTransaction) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(bankTransaction);
        transaction.commit();
    }

    public Transaction findTransactionById(int transactionId) {
        return entityManager.find(Transaction.class, transactionId);
    }

    public void deleteTransaction(int transactionId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Transaction bankTransaction = entityManager.find(Transaction.class, transactionId);
        if (bankTransaction != null) {
            entityManager.remove(bankTransaction);
        }
        transaction.commit();
    }

    public List<Transaction> getAllTransactions() {
        return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }

    public void updateTransaction(Transaction newTransaction , Integer id) {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        Transaction updatedTransaction = this.getEntityManager().find(Transaction.class, id);
        updatedTransaction.setFromAccount(newTransaction.getFromAccount());
        updatedTransaction.setToAccount(newTransaction.getToAccount());
        updatedTransaction.setTransferAmount(newTransaction.getTransferAmount());
        transaction.commit();
    }

    public List<Transaction> filterTransactionBasedOnDate(Date date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("date"), date));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
//    public static void main(String[] args) {
//        TransactionDAO transactionDAO = new TransactionDAO(ORMConfig.getEntityManager());
////        CheckingAccount checkingAccount1 = new CheckingAccount("1238","zahra",50_000.0);
////        checkingAccount1.setBalance(800_000.0);
////        CheckingAccount checkingAccount2 = new CheckingAccount("1239","soheil",50_000.0);
////        checkingAccount2.setBalance(900_000.0);
////        SavingsAccount savingsAccount = new SavingsAccount("12342","reza");
////        savingsAccount.setBalance(500_000);
////        checkingAccountDAO.createAccount(checkingAccount1);
////        checkingAccountDAO.createAccount(checkingAccount2);
////        checkingAccountDAO.createAccount(savingsAccount);
////        checkingAccountDAO.createCheckingAccount(savingsAccount);
////        checkingAccountDAO.updateCheckingAccount(checkingAccount2 , 1);
////        for (BankAccount b: checkingAccountDAO.getAllCheckingAccounts()) {
////            System.out.println(b);
////        }
//
//        BankAccountDAO bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
//        BankAccount account1 = bankAccountDAO.findAccountById(1);
//        BankAccount account2 = bankAccountDAO.findAccountById(2);
//        LocalDate localDate = LocalDate.now();
//        LocalDate parse = LocalDate.parse("2024-03-15");
//        Date currentDate = Date.valueOf(parse);
////        Transaction transaction1 = new Transaction(account1 , account2 , 10.0 , currentDate);
////        Transaction transaction2 = new Transaction(account1 , account2 , 100.0 , currentDate);
//        Transaction transaction3 = new Transaction(account1 , account2 , 1000.0 , currentDate);
////        transactionDAO.createTransaction(transaction1);
////        transactionDAO.createTransaction(transaction2);
//        transactionDAO.createTransaction(transaction3);
//        for (Transaction t: transactionDAO.findTransactionsAfterDate(currentDate)) {
//            System.out.println(t);
//        }
////        transactionDAO.findTransactionsAfterDate(currentDate);
//    }

}
