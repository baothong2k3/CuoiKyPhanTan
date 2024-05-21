import dao.CandidateDAO;
import dao.Impl.CandidateImpl;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * @ (#) TestCandidate.java    1.0    20/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCandidate {
    private CandidateDAO candidateDAO;
    EntityManager em = null;

    @BeforeEach
    void setUp() {
        candidateDAO = new CandidateImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    @Test
    public void testListCadidatesByCompanies() {
        Map<Candidate, Long> result = candidateDAO.listCadidatesByCompanies();
        for (Map.Entry<Candidate, Long> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testListCandidatesWithLongestWorking() {
        Map<Candidate, Position> result = candidateDAO.listCandidatesWithLongestWorking();
        for (Map.Entry<Candidate, Position> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void addcandidate() {
        Position p = em.find(Position.class, "P101");
        Candidate candidates = new Candidate("C124", "B", 1999, "M", "a@b.c", "123", "abc");
        candidates.setPosition(p);
        assertTrue(candidateDAO.addCandidate(candidates));
        candidateDAO.addCandidate(candidates);
    }

    @Test
    void addcandidate1() {
        Candidate candidates = new Candidate("A123", "B", 1999, "M", "a@b.c", "123", "abc");
        assertTrue(candidateDAO.addCandidate(candidates));
    }

    @Test
    void listCadidatesAndCertificates() {
        Map<Candidate, Set<Certificate>> result = candidateDAO.listCadidatesAndCertificates();
        for (Map.Entry<Candidate, Set<Certificate>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @AfterEach
    void tearDown() {
        ((CandidateImpl) candidateDAO).close();
    }
}
