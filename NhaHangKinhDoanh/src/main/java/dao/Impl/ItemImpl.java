/*
 * @ (#) ItemImpl.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import dao.ItemDAO;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ItemImpl implements ItemDAO {
    private EntityManager entityManager;

    public ItemImpl() {
        entityManager = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    @Override
    public List<Item> listItems(String supplierName) {
        supplierName = supplierName.replace("'", "''"); // escape single quotes
        String jpql = "SELECT i FROM Item i JOIN i.ingredients ig " +
                "WHERE LOWER(ig.supplierName) LIKE LOWER(:supplierName) and i.onSepecial = true";
        return entityManager.createQuery(jpql, Item.class)
                .setParameter("supplierName", "%" + supplierName + "%")
                .getResultList();
    }


    public void close() {
        entityManager.close();
    }
}
