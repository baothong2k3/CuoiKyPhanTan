/**
 * @ (#) TestExperienceDao.java      4/24/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package testALL;

import dao.ICandidateDao;
import dao.IExperience;
import dao.impl.CandidateDaoImpl;
import dao.impl.ExperienceDaoImpl;
import entity.Candidate;
import entity.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/24/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestExperienceDao {
    private IExperience experienceDao;

    @BeforeAll
    public void init() {
        experienceDao = new ExperienceDaoImpl();
    }

    @Test
    public void testListCandidatesWithLongestWorking() {
        Map<Candidate, Position> map = experienceDao.listCandidatesWithLongestWorking();
        map.entrySet().forEach(entry ->{
            System.out.println("Ung vien: " + entry.getKey().getFullName() + "\n Vi tri:  " + entry.getValue().getName());
        });
    }

    @Test
    public void testListYearsOfExperienceByPosition() {
        Map<Position, Integer> map = experienceDao.listYearsOfExperienceByPosition("C001");
        map.entrySet().forEach(entry ->{
            System.out.println("Vi tri: " + entry.getKey().getName() + "\n So nam kinh nghiem: " + entry.getValue());
        });
    }
}
