/*
 * @ (#) CadidateImpl.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 17/05/2024
 * @version: 1.0
 */

import dao.CandidateDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Experience;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CandidateImpl implements CandidateDAO {
    private EntityManager em;

    public CandidateImpl() {
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql")
                .createEntityManager();
    }

    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        String queryStr = "SELECT c, COUNT(e) FROM Candidate c JOIN c.experiences e GROUP BY c";
        List<Object[]> resultList = em.createQuery(queryStr).getResultList();
        Map<Candidate, Long> result = new HashMap<>();
        for (Object[] objects : resultList) {
            result.put((Candidate) objects[0], (Long) objects[1]);
        }
        return result;
    }

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
        for (Object[] objects : list) {
            Candidate candidate = em.find(Candidate.class, objects[0]);
            Position position = em.find(Position.class, objects[1]);
            map.put(candidate, position);
        }
        return map;
    }

    @Override
    public boolean addCandidate(Candidate candidate) {
        // Check if candidate ID starts with 'C' and is followed by at least 3 digits
        if (!candidate.getId().matches("C\\d{3,}")) {
            return false;
        }

        try {
            em.getTransaction().begin();
            em.persist(candidate);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    //    @Override
//    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
//        String queryStr = "SELECT c FROM Candidate c";
//        List<Candidate> resultList = em.createQuery(queryStr, Candidate.class).getResultList();
//        Map<Candidate, Set<Certificate>> result = new HashMap<>();
//        for (Candidate candidate : resultList) {
//            Set<Certificate> certificateSet = candidate.getCertificates().stream().collect(Collectors.toSet());
//            result.put(candidate, certificateSet);
//        }
//        return result;
//    }
    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        String queryStr = "SELECT c, cert FROM Candidate c JOIN c.certificates cert";
        List<Object[]> resultList = em.createQuery(queryStr).getResultList();
        Map<Candidate, Set<Certificate>> result = new HashMap<>();
        for (Object[] objects : resultList) {
            Candidate candidate = (Candidate) objects[0];
            Certificate certificate = (Certificate) objects[1];
            if (result.containsKey(candidate)) {
                result.get(candidate).add(certificate);
            } else {
                Set<Certificate> certificateSet = new HashSet<>();
                certificateSet.add(certificate);
                result.put(candidate, certificateSet);
            }
        }
        return result;
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
