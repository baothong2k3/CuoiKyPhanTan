import dao.BookDao;
import dao.Impl.BookImpl;
import entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

/*
 * @ (#) TestBook.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBook {
    private BookDao bookDao;
    @BeforeEach
    public void setUp() {
        bookDao = new BookImpl();
    }
    @Test
    public void testListRatedBooks() {
        List<Book> books = bookDao.listRatedBooks("Robert C. Martin", 3);
        for (Book book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void testCountBooksByAuthor() {
        Map<String, Long> authorBookCounts = bookDao.countBooksByAuthor();
        for (Map.Entry<String, Long> entry : authorBookCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    @AfterEach
    public void tearDown() {
        bookDao = null;
    }
}
