package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.Mouse;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class MouseSpawner extends EntitySpawner<Mouse> {
    public MouseSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.MOUSE);
    }
}
