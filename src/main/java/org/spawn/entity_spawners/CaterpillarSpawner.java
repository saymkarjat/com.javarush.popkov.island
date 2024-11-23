package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.herbivores.Caterpillar;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class CaterpillarSpawner extends EntitySpawner<Caterpillar> {
    public CaterpillarSpawner(IslandMap map) {
        super(map);
        super.count = 40;
    }

    @Override
    public Entity spawnEntity() {
        return null;
    }
}
