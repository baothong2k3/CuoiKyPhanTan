package dao.impl;

import dao.ICandidateDao;
import entity.Candidate;
import entity.Certificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidateDaoImpl implements ICandidateDao {
    private final EntityManager em;
    private final EntityTransaction et;
    public CandidateDaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        String query = "select c, count(e.companyName) from Experience e join Candidate c on e.candidate.id = c.id group by c";
        List<Object[]> list = em.createQuery(query, Object[].class).getResultList();
        Map<Candidate, Long> map = new HashMap<>();
        for(Object[] objects : list) {
            Candidate candidate = (Candidate) objects[0];
            Long count = (Long) objects[1];
            map.put(candidate, count);
        }
        return map;
    }

    @Override
    public boolean addCandidate(Candidate candidate) {
        try {
            et.begin();
            if(candidate.getId().matches("^C\\d{3,}$")) {
                em.persist(candidate);
                et.commit();
                return true;
            } else {
                System.out.println("Invalid candidate id");
                return false;
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        return null;
    }
}
