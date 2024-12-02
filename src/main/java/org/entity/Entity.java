package org.entity;

import lombok.Getter;
import lombok.Setter;
import org.island.Coordinate;

public abstract class Entity {
    @Getter
    @Setter
    Coordinate coordinate;
}
