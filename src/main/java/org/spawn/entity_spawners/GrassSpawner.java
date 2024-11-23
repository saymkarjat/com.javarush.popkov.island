package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.plants.Grass;
import org.fabric.EntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class GrassSpawner extends EntitySpawner<Grass> {
    public GrassSpawner(IslandMap map) {
        super(map);
        super.count = 100;
    }

    @Override
    public Entity spawnEntity() {
        return EntityFactory.createEntity(EntityType.GRASS);
    }
}
