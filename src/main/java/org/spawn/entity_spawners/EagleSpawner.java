package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.predators.Eagle;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class EagleSpawner extends EntitySpawner<Eagle> {
    public EagleSpawner(IslandMap map) {
        super(map);
        super.count = 25;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.EAGLE);
    }
}
