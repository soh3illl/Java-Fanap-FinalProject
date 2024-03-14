package org.example.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ORMConfig {
    private static final EntityManagerFactory entityManagerFactory;
    private static final EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankSystem");
        entityManager = entityManagerFactory.createEntityManager();
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
