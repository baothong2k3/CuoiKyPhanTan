/*
 * @ (#) BookImpl.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import dao.BookDao;
import entity.Book;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookImpl implements BookDao {
    private EntityManager em;

    public BookImpl() {
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    //    @Override
//    public List<Book> listRatedBooks(String author, int rating) {
//        String query = "SELECT b FROM Book b JOIN b.reviews r WHERE :author MEMBER OF b. AND r.rating >= :rating";
//        return em.createQuery(query, Book.class)
//                .setParameter("author", author)
//                .setParameter("rating", rating)
//                .getResultList();
//    }
    @Override
    public List<Book> listRatedBooks(String author, int rating) {
        String jpql = "SELECT b FROM Book b JOIN b.books_authors a JOIN b.reviews r WHERE a = :author AND r.rating >= :rating";
        return em.createQuery(jpql, Book.class)
                .setParameter("author", author)
                .setParameter("rating", rating)
                .getResultList();
    }

    @Override
    public Map<String, Long> countBooksByAuthor() {
        String sql = "SELECT ba.author, COUNT(bt.ISBN) AS translated_books_count " +
                "FROM books_authors ba " +
                "JOIN books b ON ba.ISBN = b.ISBN " +
                "JOIN book_translations bt ON b.ISBN = bt.ISBN " +
                "GROUP BY ba.author " +
                "ORDER BY ba.author";
        List<Object[]> results = em.createNativeQuery(sql).getResultList();
        Map<String, Long> authorBookCounts = new HashMap<>();
        for (Object[] result : results) {
            authorBookCounts.put((String) result[0], ((Number) result[1]).longValue());
        }
        return authorBookCounts;
    }
}
