package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.predators.Bear;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class BearSpawner extends EntitySpawner<Bear> {

    public BearSpawner(IslandMap map) {
        super(map);
        super.count = 15;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.BEAR);
    }
}
