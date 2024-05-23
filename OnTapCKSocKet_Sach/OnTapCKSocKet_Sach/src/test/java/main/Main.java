/*
 * @ {#} Main.java   1.0     03/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   03/05/2024
 * @version:    1.0
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_MSSQL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
