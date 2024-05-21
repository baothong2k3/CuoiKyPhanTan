package dao.impl;

import dao.IPositionsDao;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class PositionsDaoImpl implements IPositionsDao {
    private final EntityManager em;
    private final EntityTransaction et;
    public PositionsDaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    @Override
    public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
        String sql="select p from Position p where p.name like :name and p.salary >= :salaryFrom and p.salary <= :salaryTo";
        List<Position> listPositions = new ArrayList<>();
        try {
            et.begin();
           listPositions=em.createQuery(sql,Position.class)
                   .setParameter("name","%"+name+"%")
                   .setParameter("salaryFrom",salaryFrom)
                   .setParameter("salaryTo",salaryTo)
                   .getResultList();
            et.commit();
            return listPositions;
        }catch (Exception e){
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
