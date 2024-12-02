package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/caterpillar/caterpillar.yaml")
public class Caterpillar extends Herbivore {
    @Override
    public String toString() {
        return "\uD83D\uDC1B";
    }
}
