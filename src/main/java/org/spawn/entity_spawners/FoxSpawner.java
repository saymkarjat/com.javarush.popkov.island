package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.predators.Fox;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class FoxSpawner extends EntitySpawner<Fox> {

    public FoxSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.FOX);
    }
}
