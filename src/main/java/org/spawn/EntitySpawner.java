package org.spawn;


import org.entities.Entity;
import org.island.Coordinate;
import org.island.IslandMap;
import org.settings.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class EntitySpawner<T extends Entity> extends Spawner implements Runnable {
    protected int count;
    private IslandMap map;

    public EntitySpawner(IslandMap map) {
        this.map = map;
    }


    @Override
    public void run() {
        int counter = 0;
        while (counter < count) {
            Coordinate coordinate = generateCoordinates();
            Entity entity = spawnEntity();
            map.placeEntity(entity, coordinate);
            counter++;
        }
    }

    public Coordinate generateCoordinates() {
        int x = ThreadLocalRandom.current().nextInt(0, Settings.islandWidth + 1);
        int y = ThreadLocalRandom.current().nextInt(0, Settings.islandHeight + 1);
        return new Coordinate(x, y);
    }


    @Override
    public Entity spawnEntity() {
        return null;
    }
}
