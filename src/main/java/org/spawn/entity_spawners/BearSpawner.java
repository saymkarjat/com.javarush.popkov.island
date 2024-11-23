package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.predators.Bear;
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
