package org.renderer;

import org.entities.enumerations.EntityType;
import org.entities.herbivores.Herbivore;
import org.entities.predators.Predator;
import org.fabric.EntityFactory;
import org.island.IslandMap;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class Renderer {
    private IslandMap map;

    public Renderer(IslandMap map) {
        this.map = map;
    }
    public void printStats(){
        long count = map.getAllCells().stream()
                .mapToLong(e -> e.getAllEntitiesInCell().size())
                .sum();
        System.out.println("Всего на острове - " + count);
        printCountOfHerbivoresAndPredators();
        printCountOfEachAnimal();
    }
    public void printCountOfHerbivoresAndPredators(){
        long countHerbivore = map.getAllCells().stream()
                .flatMap(e->e.getAllEntitiesInCell().stream())
                .filter(e->e instanceof Herbivore)
                .count();

        long countPredator = map.getAllCells().stream()
                .flatMap(e->e.getAllEntitiesInCell().stream())
                .filter(e->e instanceof Predator)
                .count();
        System.out.println("Хищники: " + countPredator +" Травоядные: " + countHerbivore);

    }

    public void printCountOfEachAnimal() {
        for (EntityType type : EntityType.values()) {
            long count = map.getAllCells().stream()
                    .flatMap(cell -> cell.getAllEntitiesInCell().stream())
                    .filter(e->e.getClass().getSimpleName().toUpperCase().equals(type.name()))
                    .count();

            System.out.print(EntityFactory.createEntity(type)+"" + count + " ,");
        }
    }


}
