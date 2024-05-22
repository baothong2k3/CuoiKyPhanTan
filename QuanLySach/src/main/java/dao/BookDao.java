/*
 * @ (#) BookDao.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {
    public List<Book> listRatedBooks(String author, int rating);

    public Map<String, Long> countBooksByAuthor();
}
