package org.entity.predators;

import org.configuration.Config;

@Config("config/animals/predators/bear/bear.yaml")
public class Bear extends Predator {

    @Override
    public String toString() {
        return "🐻" ;
    }
}
