package org.island;


import org.entity.Entity;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class IslandMap {
    private int islandX;
    private int islandY;

    public IslandMap(int islandX, int islandY) {
        this.islandX = islandX;
        this.islandY = islandY;
    }

    private ConcurrentHashMap<Coordinate, Cell> worldMap = new ConcurrentHashMap<>();

    public void placeEntity(Entity entity, Coordinate coordinate) {
        entity.setCoordinate(coordinate);
        Cell cell = worldMap.getOrDefault(coordinate, new Cell());
        cell.placeEntityInCell(entity);
        worldMap.put(coordinate, cell);

    }

    public Cell getCell(Coordinate coordinate) {
        return worldMap.get(coordinate);
    }

    public void replaceEntity(Coordinate current, Coordinate target, Entity entity) {
        Cell cell = getCell(current);
        placeEntity(entity, target);
        cell.removeEntity(entity);
    }

    public Set<Cell> getAllCells() {
        return new HashSet<>(worldMap.values());
    }


}
