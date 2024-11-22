package org.island;

import lombok.AllArgsConstructor;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class IslandMap {
    private int islandX;
    private int islandY;

    private ConcurrentHashMap<Coordinate, Cell> worldMap = new ConcurrentHashMap<>();

}
