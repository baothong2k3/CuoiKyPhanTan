/**
 * @ (#) TestCandidateDao.java      4/23/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package testALL;

import dao.impl.CandidateDaoImpl;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/23/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCandidateDao {

    EntityManager em = null;
    EntityTransaction et = null;
    private CandidateDaoImpl candidateDaoImpl;
    @BeforeAll
    public void setUp() {
        candidateDaoImpl = new CandidateDaoImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
        et = em.getTransaction();
    }

    @Test
    public void testListCadidatesByCompanies() {
        Map<Candidate, Long> map = candidateDaoImpl.listCadidatesByCompanies();
        map.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println("So cong ty tung lam: " + entry.getValue());
        });
    }

    @Test
    public void testAddcandidate() {
        Position position = em.find(Position.class, "P104");
        Candidate candidate = new Candidate("C113", "Nguyen Van E", 21, "Female", "HN", "0987654321", "hay");
        candidate.setPosition(position);
        boolean check = candidateDaoImpl.addCandidate(candidate);
        System.out.println(check);
    }
}
