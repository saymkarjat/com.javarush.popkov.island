package org.entities.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/sheep/sheep.yaml")
public class Sheep extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC11";
    }
}
