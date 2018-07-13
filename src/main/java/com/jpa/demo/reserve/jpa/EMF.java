package com.jpa.demo.reserve.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
    private static EntityManagerFactory emf;

    public static void init() {
        emf = Persistence.createEntityManagerFactory("jpastart");
    }

    public static EntityManager createEmtityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();

    }
}
