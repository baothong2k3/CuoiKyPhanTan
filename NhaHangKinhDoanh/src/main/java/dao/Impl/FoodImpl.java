/*
 * @ (#) FoodImpl.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import dao.FoodDAO;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodImpl implements FoodDAO {
    private EntityManager entityManager;

    public FoodImpl() {
        entityManager = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    @Override
    public boolean addFood(Food food) {
        String id = food.getId();
        if (!id.matches("F\\d{3,}")) {
            return false;
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(food);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<Food, Double> listFoodAndCost() {
        Map<Food, Double> foodCosts = new LinkedHashMap<>();

        String sql = "SELECT i.name, (SUM(ing.price * ing.quantity) + (f.preparation_time + f.serving_time) * 0.2) as cost " +
                "FROM items i " +
                "INNER JOIN foods f ON i.id = f.id " +
                "INNER JOIN items_ingredients ii ON i.id = ii.item_id " +
                "INNER JOIN ingredients ing ON ii.ingredient_id = ing.ingredient_id " +
                "GROUP BY i.name, f.preparation_time, f.serving_time " +
                "ORDER BY cost DESC";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        for (Object[] result : results) {
            String name = (String) result[0];
            Double cost = (Double) result[1];
            Food food = new Food();
            food.setName(name);
            foodCosts.put(food, cost);
        }

        return foodCosts;
    }

    public void close() {
        entityManager.close();
    }

}
