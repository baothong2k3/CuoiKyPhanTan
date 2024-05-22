import dao.Impl.ReviewImpl;
import dao.ReviewDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * @ (#) TestReview.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestReview {
    private ReviewDao reviewDao;
    @BeforeEach
    public void setUp() {
        reviewDao = new ReviewImpl();
    }
    @Test
    public void testUpdateReviews() {
        assertTrue(reviewDao.updateReviews("888-0132350800", "11", 4, "Good book"));
    }
    @AfterEach
    public void tearDown() {
        reviewDao = null;
    }
}
