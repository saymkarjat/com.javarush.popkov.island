package org.entities.herbivores;

import org.configuration.Config;
import org.entities.predators.Predator;

@Config("config/animals/herbivores/boar/boar.yaml")
public class Boar extends Herbivore {
    @Override
    public String toString() {
        return "\uD83D\uDC17" ;
    }
}
