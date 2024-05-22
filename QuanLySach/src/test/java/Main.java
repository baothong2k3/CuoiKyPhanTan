/*
 * @ (#) Main.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        et.commit();
    }
}
