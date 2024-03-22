package org.example.service;

import org.example.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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

    public void deleteAccountById(int accountId) {
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

    public void updateAccount(Map<String, Object> updates, Integer id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        BankAccount updatedAccount = this.getEntityManager().find(BankAccount.class, id);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String attributeName = entry.getKey();
            Object attributeValue = entry.getValue();
            if (attributeName.equals("balance")){
                attributeValue = Double.parseDouble((String) attributeValue);
            }
            String setterMethodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            updatedAccount.getClass().getMethod(setterMethodName, attributeValue.getClass()).invoke(updatedAccount, attributeValue);

        }

        transaction.commit();
    }

    public List<BankAccount> filterBalanceByAmount(double balance) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteriaQuery = criteriaBuilder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteriaQuery.from(BankAccount.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.greaterThan(root.get("balance"), balance));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
