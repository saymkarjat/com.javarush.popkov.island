package org.island;

import org.entities.Animal;
import org.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell implements Runnable {
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
    /*
        - Задания роста растений (вырасти на кол-во)
        - Задания жизненного цикла животных (worker, eat, reproduce, move)
        - Задания вывода статистики по системе (sout) - текущее кол-во животных по видам в целом на острове + вес травы
    */
    @Override
    public void run() {

    }
}
