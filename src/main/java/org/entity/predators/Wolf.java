package org.entity.predators;

import org.configuration.Config;

@Config("config/animals/predators/wolf/wolf.yaml")
public class Wolf extends Predator{
    @Override
    public String toString() {
        return "\uD83D\uDC3A" ;
    }
}
