package org.entities.predators;

import org.configuration.Config;

@Config("config/animals/predators/boa/boa.yaml")
public class Boa extends Predator{
    @Override
    public String toString() {
        return "\uD83D\uDC0D" ;
    }
}
