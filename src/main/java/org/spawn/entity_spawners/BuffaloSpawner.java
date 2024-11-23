package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.Buffalo;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class BuffaloSpawner extends EntitySpawner<Buffalo> {

    public BuffaloSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.BUFFALO);
    }
}
