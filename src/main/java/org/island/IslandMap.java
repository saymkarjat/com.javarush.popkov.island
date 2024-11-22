package org.island;


import org.entities.Entity;

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
        Cell cell = worldMap.getOrDefault(coordinate, new Cell());
        cell.placeEntityInCell(entity);
        entity.setCoordinate(coordinate);
    }

    public Cell getCell(Coordinate coordinate) {
        return worldMap.get(coordinate);
    }

    public void replaceEntity(Coordinate current, Coordinate target, Entity entity) {
        Cell cell = getCell(current);
        cell.removeEntity(entity);
        placeEntity(entity, target);
    }

}
