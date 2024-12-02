package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.predators.Eagle;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class EagleSpawner extends EntitySpawner<Eagle> {
    public EagleSpawner(IslandMap map) {
        super(map);
        super.count = 40;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.EAGLE);
    }
}
