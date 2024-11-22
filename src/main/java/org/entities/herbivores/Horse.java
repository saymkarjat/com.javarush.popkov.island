package org.entities.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/horse/horse.yaml")
public class Horse extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC0E";
    }
}
