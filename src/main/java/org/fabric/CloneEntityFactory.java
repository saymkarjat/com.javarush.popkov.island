package org.fabric;

import org.entities.Animal;
import org.entities.Entity;
import org.entities.enumerations.EntityType;

import java.util.HashMap;
import java.util.Map;

public class CloneEntityFactory {
    public static Map<EntityType, Entity> typeEntityMap = new HashMap<>();

    static {
        fillTheMap();
    }

    public static void fillTheMap() {
        for (EntityType entity : EntityType.values()) {
            Entity prototype = EntityFactory.createEntity(entity);
            typeEntityMap.put(entity, prototype);
        }
    }

    public static Animal createAnimalWithClone(EntityType entity) {
        Animal prototype = (Animal) typeEntityMap.get(entity);
        Animal clone;
        try {
            clone = (Animal) prototype.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }
}
