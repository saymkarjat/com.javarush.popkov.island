package org.spawn.entity_spawners;

import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.Goat;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class GoatSpawner extends EntitySpawner<Goat> {
    public GoatSpawner(IslandMap map) {
        super(map);
        super.count = 30;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.GOAT);
    }
}
