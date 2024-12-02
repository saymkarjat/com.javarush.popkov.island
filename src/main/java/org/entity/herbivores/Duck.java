package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/duck/duck.yaml")
public class Duck extends Herbivore{
    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
