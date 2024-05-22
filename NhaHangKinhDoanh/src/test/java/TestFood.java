/*
 * @ (#) TestFood.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.FoodDAO;
import dao.Impl.FoodImpl;
import entity.Food;
import entity.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFood {
    private FoodDAO foodDAO;
    @BeforeEach
    public void setUp() {
        foodDAO = new FoodImpl();
    }
    @Test
    public void addFood() {
        Food f = new Food("F001", "Banh Mi", 20000, "Banh Mi thit ngon", false, Type.DESSERT, 10, 5);
        assertTrue(foodDAO.addFood(f));
    }
    @Test
    public void listFoodAndCost() {
        Map<Food, Double> foodCosts = foodDAO.listFoodAndCost();
        for(Map.Entry<Food, Double> entry : foodCosts.entrySet()) {
            System.out.println(entry.getKey().getName() + " - " + entry.getValue());
        }
        assertTrue(foodCosts.size() > 0);
    }
    @AfterEach
    public void tearDown() {
        ((FoodImpl) foodDAO).close();
    }
}
