package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.Horse;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class HorseSpawner extends EntitySpawner<Horse> {
    public HorseSpawner(IslandMap map) {
        super(map);
        super.count = 20;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.HORSE);
    }
}
