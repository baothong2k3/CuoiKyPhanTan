/**
 * @ (#) ExperienceDaoImpl.java      4/24/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao.impl;

import dao.IExperience;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/24/2024
 */
public class ExperienceDaoImpl implements IExperience {
    EntityManager em = null;
    EntityTransaction et = null;
    public ExperienceDaoImpl() {
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() {
        String query = "SELECT e.candidate_id, e.position_id, \n" +
                "       DATEDIFF(year, MIN(e.from_date), MAX(e.to_date)) AS tgLamViec\n" +
                "FROM experiences e\n" +
                "JOIN positions p ON e.position_id = p.position_id\n" +
                "GROUP BY e.candidate_id, e.position_id\n" +
                "HAVING DATEDIFF(year, MIN(e.from_date), MAX(e.to_date)) >= (\n" +
                "    SELECT MAX(DATEDIFF(year, from_date, to_date))\n" +
                "    FROM experiences\n" +
                "    WHERE candidate_id = e.candidate_id\n" +
                ")";
        Map<Candidate, Position> map = new HashMap<>();
        List<Object[]> list = em.createNativeQuery(query).getResultList();
        for(Object[] objects : list) {
            Candidate candidate = em.find(Candidate.class, objects[0]);
            Position position = em.find(Position.class, objects[1]);
            map.put(candidate, position);
        }
        return map;
    }

    @Override
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
        String query = "SELECT e.position_id,\n" +
                "       DATEDIFF(year, MIN(e.from_date), MAX(e.to_date)) AS tgLamViec\n" +
                "FROM experiences e\n" +
                "JOIN positions p ON e.position_id = p.position_id\n" +
                "where e.candidate_id = :candidate_id\n" +
                "GROUP BY e.position_id";
        Map<Position, Integer> map = new HashMap<>();
        List<Object[]> list = em.createNativeQuery(query).setParameter("candidate_id", candidateID).getResultList();
        for(Object[] objects : list) {
            Position position = em.find(Position.class, objects[0]);
            Integer years = (Integer) objects[1];
            map.put(position, years);
        }
        return map;
    }
}
