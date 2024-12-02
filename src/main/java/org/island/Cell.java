package org.island;

import lombok.Setter;
import org.entity.Animal;
import org.entity.Entity;
import org.entity.plants.Grass;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Cell implements Runnable {
    private CopyOnWriteArrayList<Entity> cell = new CopyOnWriteArrayList<>();
    @Setter
    private IslandMap map;

    public long getEntityCountInCurrentCell(Entity entity) {
        return cell.stream()
                .filter(e -> entity.getClass().isInstance(e))
                .count();
    }

    public boolean isCapacityAvailable(Animal animal) {
        long limit = getEntityCountInCurrentCell(animal) + 1;
        return limit <= animal.getMaxAmount();
    }

    public List<Entity> getAllEntitiesInCell() {
        return Collections.unmodifiableList(cell);
    }

    public void removeEntity(Entity entity) {
        cell.remove(entity);
    }

    public void placeEntityInCell(Entity entity) {
        cell.add(entity); // Добавляет элемент в конец очереди
    }

    private List<Grass> getAllGrass(){
        return cell.stream()
                .filter(e -> e instanceof Grass)
                .map(e -> (Grass)e)
                .collect(Collectors.toList());
    }

    private List<Animal> getAllAnimals(){
        return cell.stream()
                .filter(e -> e instanceof Animal)
                .map(e -> (Animal)e)
                .collect(Collectors.toList());
    }

    /*
        - Задания роста растений (вырасти на кол-во)
        - Задания жизненного цикла животных (worker, eat, reproduce, move)

    */
    @Override
    public void run() {
        List<Grass> grass = getAllGrass();
        List<Animal> animals = getAllAnimals();
        //grass grow()
        //grass.forEach(Grass::grow);
//        worker, eat, reproduce, move
        animals.forEach(Animal::reduceStarvation);
        animals.forEach(e->e.eat(map));
        //animals.forEach(e->e.reproduce(map));
        animals.forEach(e->e.move(map));

    }
}
