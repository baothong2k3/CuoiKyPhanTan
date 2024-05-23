/*
 * @ {#} BookImpl.java   1.0     03/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao.impl;

import dao.BookDao;
import entity.Book;
import entity.Person;
import entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   03/05/2024
 * @version:    1.0
 */
public class BookImpl implements BookDao {
    private EntityManager em;

    public BookImpl() {
        em = Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }
    @Override
    public List<Book> listRatedBooks(String author, int rating) {
        return em.createNamedQuery("Book.listRatedBooks", Book.class)
                .setParameter("author", author)
                .setParameter("rating", rating)
                .getResultList();
    }

    @Override
    public Map<String, Long> countBooksByAuthor() {
       Map<String, Long> map=new TreeMap<>();
       List<?> result=em.createNamedQuery("Book.countBooksByAuthor").getResultList();
       result
               .stream()
               .map(o->(Object[])o)
                .forEach(o->map.put((String)o[0],(Long)o[1]));
         return map;
    }

    @Override
    public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Book book = em.find(Book.class, isbn);
            Person person = em.find(Person.class, readerID);
            if (book == null || person == null) {
                return false;
            }
            if (rating < 1 || rating > 5) {
                return false;
            }
            if (comment == null || comment.trim().isEmpty()) {
                return false;
            }
            Reviews reviews = new Reviews(rating, comment, book, person);
            em.persist(reviews);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        BookDao bookImpl = new BookImpl();
        List<Book> books = bookImpl.listRatedBooks("h", 4);
        books.forEach(System.out::println);
    }
}
