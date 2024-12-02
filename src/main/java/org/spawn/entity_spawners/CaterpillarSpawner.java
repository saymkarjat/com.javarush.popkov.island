package org.spawn.entity_spawners;

import org.entity.Entity;
import org.entity.enumerations.EntityType;
import org.entity.herbivores.Caterpillar;
import org.fabric.CloneEntityFactory;
import org.island.IslandMap;
import org.spawn.EntitySpawner;

public class CaterpillarSpawner extends EntitySpawner<Caterpillar> {
    public CaterpillarSpawner(IslandMap map) {
        super(map);
        super.count = 100;
    }

    @Override
    public Entity spawnEntity() {
        return CloneEntityFactory.createAnimalWithClone(EntityType.CATERPILLAR);
    }
}
