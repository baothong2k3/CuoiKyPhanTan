/*
 * @ (#) PositionDAO.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 17/05/2024
 * @version: 1.0
 */

import entity.Position;

import java.util.List;
import java.util.Map;

public interface PositionDAO {
    public List<Position> listPositions(String name , double salaryFrom , double salaryTo);
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID);
}
