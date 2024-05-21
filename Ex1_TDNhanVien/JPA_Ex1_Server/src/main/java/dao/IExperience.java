/**
 * @ (#) IExperience.java      4/24/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entity.Candidate;
import entity.Position;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/24/2024
 */
public interface IExperience {
    public  Map<Candidate, Position>  listCandidatesWithLongestWorking();
    public Map<Position, Integer>  listYearsOfExperienceByPosition(String candidateID);
}
