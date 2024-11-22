package org.entities.predators;

import org.configuration.Config;

@Config("config/animals/predators/fox/fox.yaml")
public class Fox extends Predator{
    @Override
    public String toString() {
        return "\uD83E\uDD8A" ;
    }
}
