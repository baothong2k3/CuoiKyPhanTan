/*
 * @ {#} BookDao.java   1.0     03/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import entity.Book;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   03/05/2024
 * @version:    1.0
 */
public interface BookDao {
    public List<Book> listRatedBooks(String author, int rating);
    public Map<String, Long> countBooksByAuthor();
    public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
