/*
 * @ (#) ReviewDao.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

public interface ReviewDao {
    public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
