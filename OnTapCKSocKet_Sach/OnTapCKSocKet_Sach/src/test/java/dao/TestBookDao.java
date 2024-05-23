/*
 * @ {#} TestBookDao.java   1.0     04/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import dao.impl.BookImpl;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   04/05/2024
 * @version:    1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBookDao {
    private EntityManager em;
    private BookDao bookDao;
    @BeforeAll
    public void setUp() {
        bookDao = new BookImpl();
        em = Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }
    @Test
    public void testListRatedBooks() {
        List<Book> list = bookDao.listRatedBooks("Robert C. Martin", 3);
        list.forEach(System.out::println);
    }
    @Test
    public void testCountBooksByAuthor() {
        Map<String, Long> map = bookDao.countBooksByAuthor();
        map.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        });
    }
    @Test
    public void testUpdateReviews() {
        boolean kq= bookDao.updateReviews("978-0-306-40615-7", "P001", 5, "Good book");
        System.out.println(kq);
    }
    @AfterAll
    public void tearDown() {
        em.close();
    }
}
