import dao.Impl.ItemImpl;
import dao.ItemDAO;
import entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

/*
 * @ (#) TestItem.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestItem {
    private ItemDAO itemDAO;
    @BeforeEach
    public void setUp() {
        itemDAO = new ItemImpl();
    }
    @Test
    public void listItems() {
        List<Item> items = itemDAO.listItems("Anna Food Distributors");
        items.forEach(System.out::println);
    }
    @AfterEach
    public void tearDown() {
        ((ItemImpl) itemDAO).close();
    }
}
