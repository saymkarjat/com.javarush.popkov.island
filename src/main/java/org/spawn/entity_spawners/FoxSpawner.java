package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.predators.Fox;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class FoxSpawner extends EntitySpawner<Fox> {

    public FoxSpawner(IslandMap map) {
        super(map);
        super.count = 20;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.FOX);
    }
}
