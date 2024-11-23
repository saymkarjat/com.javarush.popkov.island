package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.Rabbit;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class RabbitSpawner extends EntitySpawner<Rabbit> {
    public RabbitSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.RABBIT);
    }
}
