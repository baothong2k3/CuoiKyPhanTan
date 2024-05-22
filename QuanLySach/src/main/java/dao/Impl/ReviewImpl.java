/*
 * @ (#) ReviewImpl.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import dao.ReviewDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class ReviewImpl implements ReviewDao {
    private EntityManager em;
    private EntityTransaction transaction;
    public ReviewImpl() {
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
        transaction = em.getTransaction();
    }
    @Override
    public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
        EntityTransaction transaction = null;
        try {
            int id = Integer.parseInt(readerID);
            transaction = em.getTransaction();
            transaction.begin();

            // Check if a review already exists
            List results = em.createNativeQuery("SELECT * FROM reviews WHERE person_id = ? AND ISBN = ?")
                    .setParameter(1, id)
                    .setParameter(2, isbn)
                    .getResultList();

            int result;
            if (results.isEmpty()) {
                // Insert a new review
                result = em.createNativeQuery("INSERT INTO reviews (rating, comment, person_id, ISBN) VALUES (?, ?, ?, ?)")
                        .setParameter(1, rating)
                        .setParameter(2, comment)
                        .setParameter(3, id)
                        .setParameter(4, isbn)
                        .executeUpdate();
            } else {
                // Update the existing review
                result = em.createNativeQuery("UPDATE reviews SET rating = ?, comment = ? WHERE person_id = ? AND ISBN = ?")
                        .setParameter(1, rating)
                        .setParameter(2, comment)
                        .setParameter(3, id)
                        .setParameter(4, isbn)
                        .executeUpdate();
            }

            transaction.commit();
            return result > 0;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
