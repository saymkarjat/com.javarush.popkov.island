package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/boar/boar.yaml")
public class Boar extends Herbivore {
    @Override
    public String toString() {
        return "\uD83D\uDC17" ;
    }
}
