package org.entities;


import lombok.Getter;
import org.entities.enumerations.Direction;
import org.entities.enumerations.EntityType;
import org.entities.plants.Grass;
import org.fabric.CloneEntityFactory;
import org.island.Cell;
import org.island.Coordinate;
import org.island.IslandMap;
import org.settings.Settings;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Animal extends Entity implements Cloneable, Reproducible {
    private int maxSpeed;
    private String name;
    private double weight;
    private int maxAmount;
    private double maxFood;
    private double starvation;

    @Override
    public void reproduce(IslandMap map) {
        Cell cell = map.getCell(this.getCoordinate());
        long countOfAnimals = cell.getEntityCountInCurrentCell(this);
        if (countOfAnimals >= 2 && cell.isCapacityAvailable(this)){
            Animal child = CloneEntityFactory.createAnimalWithClone(EntityType.valueOf(this.getClass().getSimpleName().toUpperCase()));
            cell.placeEntityInCell(child);
        }
    }

    public void eat(IslandMap map){
        Cell cell = map.getCell(this.getCoordinate());
        Entity victim = cell.getAllEntitiesInCell().stream()
                .filter(e -> Settings.probabilities.get(e.getClass().getSimpleName()).containsKey(e.getClass().getSimpleName()))
                .findFirst().orElse(null);
        if (victim == null){
            return;
        }
        if (isEatingSuccessful(getProbability(victim))){
            if (victim instanceof Grass){
                double grassAmount = ((Grass) victim).getMaxAmount()/4;
                setStarvation(starvation + grassAmount);
                ((Grass) victim).setAmount(((Grass) victim).getAmount() - grassAmount);
            }
            if (victim instanceof Animal){
                setStarvation(starvation + ((Animal) victim).getWeight());
                ((Animal) victim).die(map);
            }
        }

    }

    private int getProbability(Entity victim){
         return Settings.probabilities.get(this.getName()).get(victim.getClass().getSimpleName());
    }
    private boolean isEatingSuccessful(int probability){
        if (ThreadLocalRandom.current().nextInt(0, 101) <= probability){
            return true;
        }
        return false;
    }

    public void move(IslandMap islandMap) {
        Coordinate start = this.coordinate;
        Coordinate target = generateCorrectCoordinate(start);
        Cell targetCell = islandMap.getCell(target);
        if (targetCell == null || targetCell.isCapacityAvailable(this)) {
            islandMap.replaceEntity(start, target, this);
        }
    }

    private Coordinate generateCorrectCoordinate(Coordinate start) {
        int x = start.getX();
        int y = start.getY();
        Direction direction;
        int shift;
        do {
            direction = chooseDirection();
            shift = chooseCoordinateShiftForMove();
        }
        while (!isMovementCorrect(direction, shift));

        if (direction == Direction.RIGHT) {
            x += shift;
        } else if (direction == Direction.LEFT) {
            x -= shift;
        } else if (direction == Direction.UP) {
            y -= shift;
        } else if (direction == Direction.DOWN) {
            y += shift;
        }
        return new Coordinate(x, y);
    }
    private boolean isMovementCorrect(Direction direction, int shift) {
        int o = switch (direction) {
            case LEFT, RIGHT -> this.coordinate.getX();
            case UP, DOWN -> this.coordinate.getY();
        };
        if (o + shift <= Settings.islandWidth && o + shift <= Settings.islandHeight && o - shift >= 0) {
            return true;
        }
        return false;
    }
    private Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }
    private int chooseCoordinateShiftForMove() {
        return ThreadLocalRandom.current().nextInt(maxSpeed);
    }

    public void die(IslandMap map){
        Cell cell = map.getCell(this.getCoordinate());
        cell.removeEntity(this);
    }
//worker
    public void reduceStarvation(){
        setStarvation(starvation - maxFood/10);
    }

    public boolean isDead(){
        if (starvation == 0){
            return true;
        }
        return false;
    }
    public void setStarvation(double starvation) {
        if (starvation >= this.maxFood) {
            this.starvation = this.maxFood;
        } else if (starvation <= 0) {
            this.starvation = 0;
        } else {
            this.starvation = starvation;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
