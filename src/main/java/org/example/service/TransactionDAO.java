package org.example.service;

import org.example.model.BankAccount;
import org.example.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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

    public void updateTransaction(Map<String, Object> updates, Integer id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        Transaction updatedTransaction = this.getEntityManager().find(Transaction.class, id);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String attributeName = entry.getKey();
            Object attributeValue = entry.getValue();
            String setterMethodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            if (attributeValue.getClass().getSuperclass().equals(BankAccount.class)) {
                updatedTransaction.getClass().getMethod(setterMethodName, attributeValue.getClass().getSuperclass()).invoke(updatedTransaction, attributeValue);

            } else {
                updatedTransaction.getClass().getMethod(setterMethodName, attributeValue.getClass()).invoke(updatedTransaction, attributeValue);

            }
        }
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

}
