package org.example.model.DAOs;

import org.example.model.AccountHolder;
import org.example.model.BankEmployee;
import org.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }


    public void createUserAccount(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }
    public User findUserById(int userId) {
        entityManager.clear();
        return entityManager.find(User.class, userId);
    }

    public void deleteUserById(int userId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
        }
        transaction.commit();
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT b FROM User b", User.class).getResultList();
    }

    public void updateUser(Map<String, Object> updates, Integer id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        User updatedUser = this.getEntityManager().find(User.class, id);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String attributeName = entry.getKey();
            Object attributeValue = entry.getValue();
            String setterMethodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            updatedUser.getClass().getMethod(setterMethodName, attributeValue.getClass()).invoke(updatedUser, attributeValue);

        }

        transaction.commit();
    }

    public List<AccountHolder> getAllAccountHolders() {
        return entityManager.createQuery("SELECT b FROM AccountHolder b", AccountHolder.class).getResultList();
    }

    public List<BankEmployee> getAllBankEmployee() {
        return entityManager.createQuery("SELECT b FROM BankEmployee b", BankEmployee.class).getResultList();
    }

    public List<User> getUserByUsernameAndPassword(String username , String password){
        return entityManager.createQuery("SELECT u FROM User u where u.username = :username " +
                "and u.password = :password", User.class).setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
    }
}
