package org.fabric;

import org.configuration.YamlConfigLoader;
import org.entities.Animal;
import org.entities.Entity;
import org.entities.enumerations.EntityType;
import org.entities.herbivores.*;
import org.entities.plants.Grass;
import org.entities.predators.*;

public class EntityFactory {

    public static Entity createEntity(EntityType entityType) {
        Entity entity = switch (entityType) {
            case BUFFALO -> new Buffalo();
            case BEAR -> new Bear();
            case HORSE -> new Horse();
            case DEER -> new Deer();
            case BOAR -> new Boar();
            case SHEEP -> new Sheep();
            case GOAT -> new Goat();
            case WOLF -> new Wolf();
            case BOA -> new Boa();
            case FOX -> new Fox();
            case EAGLE -> new Eagle();
            case RABBIT -> new Rabbit();
            case DUCK -> new Duck();
            case MOUSE -> new Mouse();
            case CATERPILLAR -> new Caterpillar();
            case GRASS -> new Grass();
        };
        if (entity instanceof Animal){
            YamlConfigLoader.loadAnimalFieldsConfig((Animal) entity);
        }
        return entity;
    }
    }
