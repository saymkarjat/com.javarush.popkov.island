package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/goat/goat.yaml")
public class Goat extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }
}
