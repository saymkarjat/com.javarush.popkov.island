package org.entities.herbivores;

import org.configuration.Config;

@Config("config/animals/herbivores/mouse/mouse.yaml")
public class Mouse extends Herbivore{
    @Override
    public String toString() {
        return "\uD83D\uDC01";
    }
}
