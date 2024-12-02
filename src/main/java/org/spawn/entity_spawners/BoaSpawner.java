package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.predators.Boa;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class BoaSpawner extends EntitySpawner<Boa> {

    public BoaSpawner(IslandMap map) {
        super(map);
        super.count = 20;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.BOA);
    }
}
