package testALL;

import dao.impl.PositionsDaoImpl;
import entity.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPosition {
    private PositionsDaoImpl positionsDaoImpl;
    @BeforeAll
    public void setUp() {
        positionsDaoImpl = new PositionsDaoImpl();
    }
    @Test
    public void testListPositions() {
        List<Position> list= positionsDaoImpl.listPositions("Analyst", 10000, 20000);
        for (Position position : list) {
            System.out.println(position);
        }
    }
    @AfterAll
    public void tearDown() {
        positionsDaoImpl = null;
    }

}
