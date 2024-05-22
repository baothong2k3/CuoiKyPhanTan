/*
 * @ (#) FoodDAO.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import entity.Food;

import java.util.Map;

public interface FoodDAO {
    public boolean addFood(Food food);
    public Map<Food, Double>  listFoodAndCost();
}
