package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/rabbit/rabbit.yaml")
public class Rabbit extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
