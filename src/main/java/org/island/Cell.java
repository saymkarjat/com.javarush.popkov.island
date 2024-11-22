package org.island;

import org.entities.Animal;
import org.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    private CopyOnWriteArrayList<Entity> cell = new CopyOnWriteArrayList<>();

    public long getEntityCountInCurrentCell(Entity entity) {
        return cell.stream()
                .filter(e -> e.getClass() == entity.getClass())
                .count();

    }

    public boolean isCapacityAvailable(Animal animal) {
        long limit = getEntityCountInCurrentCell(animal) + 1;
        if (limit > animal.getMaxAmount()) {
            return false;
        }
        return true;
    }

    public List<Entity> getAllEntitiesInCell() {
        return Collections.unmodifiableList(cell);
    }

    public void removeEntity(Entity entity) {
        cell.remove(entity);
    }

    public void placeEntityInCell(Entity entity) {
        cell.add(entity);
    }
}
