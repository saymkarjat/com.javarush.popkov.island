package org.entities.predators;

import org.configuration.Config;

@Config("config/animals/predators/eagle/eagle.yaml")
public class Eagle extends Predator{
    @Override
    public String toString() {
        return "\uD83E\uDD85" ;
    }
}
