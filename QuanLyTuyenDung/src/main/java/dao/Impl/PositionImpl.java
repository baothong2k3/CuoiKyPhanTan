/*
 * @ (#) PositionImpl.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 17/05/2024
 * @version: 1.0
 */

import dao.PositionDAO;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionImpl implements PositionDAO {
    private EntityManager em;

    public PositionImpl() {
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql")
                .createEntityManager();
    }

    @Override
    public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
        String queryStr = "SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary BETWEEN :salaryFrom AND :salaryTo ORDER BY p.name";
        return em.createQuery(queryStr, Position.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .getResultList();
    }

    @Override
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
        String queryStr = "SELECT e.position, SUM(YEAR(e.toDate) - YEAR(e.fromDate)) " +
                "FROM Experience e " +
                "WHERE e.candidate.id = :candidateID " +
                "GROUP BY e.position";
        List<Object[]> resultList = em.createQuery(queryStr)
                .setParameter("candidateID", candidateID)
                .getResultList();
        Map<Position, Integer> result = new HashMap<>();
        for (Object[] objects : resultList) {
            result.put((Position) objects[0], ((Long) objects[1]).intValue());
        }
        return result;
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
