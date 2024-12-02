package org.renderer;

import org.entity.Animal;
import org.entity.enumerations.EntityType;
import org.entity.herbivores.Herbivore;
import org.entity.predators.Predator;
import org.fabric.CloneEntityFactory;
import org.fabric.EntityFactory;
import org.island.IslandMap;

public class Renderer {
    private IslandMap map;

    public Renderer(IslandMap map) {
        this.map = map;
    }
    public void printStats(){
        long count = map.getAllCells().stream()
                .flatMap(e->e.getAllEntitiesInCell().stream())
                .filter(e->e instanceof Animal)
                .count();
        System.out.println("Всего на острове - " + count);
        printCountOfHerbivoresAndPredators();
        printCountOfEachAnimal();
        System.out.println();
    }
    public void printCountOfHerbivoresAndPredators(){
        System.out.println("Хищники: " + printCountOfPredators() +" Травоядные: " + printCountOfHerbivores());

    }
    public long printCountOfHerbivores(){
        return map.getAllCells().stream()
                .flatMap(e->e.getAllEntitiesInCell().stream())
                .filter(e->e instanceof Herbivore)
                .count();
    }

    public long printCountOfPredators(){
        return map.getAllCells().stream()
                .flatMap(e->e.getAllEntitiesInCell().stream())
                .filter(e->e instanceof Predator)
                .count();
    }


    public void printCountOfEachAnimal() {
        for (EntityType type : EntityType.values()) {
            long count = map.getAllCells().stream()
                    .flatMap(cell -> cell.getAllEntitiesInCell().stream())
                    .filter(e->e.getClass().getSimpleName().toUpperCase().equals(type.name()))
                    .count();

            System.out.print(CloneEntityFactory.typeEntityMap.get(type) +"" + count + "  ");
        }
        System.out.println();
    }


}
