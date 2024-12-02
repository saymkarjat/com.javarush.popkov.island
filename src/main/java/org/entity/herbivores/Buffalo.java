package org.entity.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/buffalo/buffalo.yaml")
public class Buffalo extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC03";
    }
}
