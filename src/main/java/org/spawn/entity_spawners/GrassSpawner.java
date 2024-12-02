package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.plants.Grass;
import org.fabric.EntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class GrassSpawner extends EntitySpawner<Grass> {
    public GrassSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return EntityFactory.createEntity(EntityType.GRASS);
    }
}
