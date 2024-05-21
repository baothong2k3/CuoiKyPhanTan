/*
 * @ (#) TestPosition.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.Impl.PositionImpl;
import dao.PositionDAO;
import entity.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPosition {
    private PositionDAO positionDAO;

    @BeforeEach
    void setUp() {
        positionDAO = new PositionImpl();
    }

    @Test
    public void testListPositions() {
        List<Position> list = positionDAO.listPositions("Analyst", 10000, 20000);
        for (Position position : list) {
            System.out.println(position);
        }
    }

    @Test
    public void testListYearsOfExperienceByPosition() {
        Map<Position, Integer> map = positionDAO.listYearsOfExperienceByPosition("C101");
        for (Map.Entry<Position, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @AfterEach
    void tearDown() {
        ((PositionImpl) positionDAO).close();
    }
}
