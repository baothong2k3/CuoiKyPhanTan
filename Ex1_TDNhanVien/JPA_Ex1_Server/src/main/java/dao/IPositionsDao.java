package dao;

import entity.Position;

import java.util.List;

public interface IPositionsDao {
    public List<Position> listPositions(String name,double salaryFrom, double salaryTo);
}
