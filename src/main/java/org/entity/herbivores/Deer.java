package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/deer/deer.yaml")
public class Deer extends Herbivore{
    @Override
    public String toString() {
        return "\uD83E\uDD8C";
    }
}
