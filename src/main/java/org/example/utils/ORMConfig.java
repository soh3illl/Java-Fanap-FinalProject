package org.example.utils;
//
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;

public class ORMConfig {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;


    public static void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("bankSystem");
        entityManager = entityManagerFactory.createEntityManager();
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
