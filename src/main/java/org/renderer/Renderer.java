package org.renderer;

import org.island.IslandMap;

public class Renderer {
    private IslandMap map;

    public Renderer(IslandMap map) {
        this.map = map;
    }
    public void printStats(){
        long count = map.getAllCells().stream()
                .mapToLong(e -> e.getAllEntitiesInCell().size())
                .sum();
        System.out.println(count);
    }


}
