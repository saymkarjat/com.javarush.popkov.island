package org.entity;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.entity.enumerations.Direction;
import org.entity.enumerations.EntityType;
import org.entity.plants.Grass;
import org.fabric.CloneEntityFactory;
import org.island.Cell;
import org.island.Coordinate;
import org.island.IslandMap;
import org.settings.Settings;

import java.util.concurrent.ThreadLocalRandom;
@Slf4j
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
            int chance = 5;
            if (ThreadLocalRandom.current().nextInt(100) < chance) {
                Animal child = CloneEntityFactory.createAnimalWithClone(EntityType.valueOf(this.getClass().getSimpleName().toUpperCase()));
                log.info("{} родился в х = {}, y = {}", child, this.getCoordinate().getX(), this.getCoordinate().getY());
                child.setCoordinate(this.getCoordinate());
                cell.placeEntityInCell(child);
            }
        }
    }

    public void eat(IslandMap map){
        if (this.isDead()){
            this.die(map);
            return;
        }
        Cell cell = map.getCell(this.getCoordinate());
        Entity victim = cell.getAllEntitiesInCell().stream()
                .filter(e -> Settings.probabilities.get(this.getClass()).containsKey(e.getClass()))
                .findFirst().orElse(null);
        if (victim == null){
            return;
        }
        if (isEatingSuccessful(getProbability(victim))){
            if (victim instanceof Grass){
                if (((Grass) victim).getAmount() == 0){
                    return;
                }
                double grassAmount = ((Grass) victim).getAmount()/3;
                setStarvation(starvation + grassAmount);
                ((Grass) victim).setAmount(((Grass) victim).getAmount() - grassAmount);
                //System.out.println(this+"eat"+victim);
            }
            if (victim instanceof Animal){
                setStarvation(starvation + ((Animal) victim).getWeight());
                log.info("{} eat {}, x = {}, y = {}.", this, victim, this.getCoordinate().getX(), this.getCoordinate().getY());
                //System.out.println(this+"eat"+victim);
                ((Animal) victim).die(map);

            }
        }
    }

    private int getProbability(Entity victim){
         return Settings.probabilities.get(this.getClass()).get(victim.getClass());
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
        if (start == target){
            return;
        }
        Cell targetCell = islandMap.getCell(target);
        if (targetCell == null || targetCell.isCapacityAvailable(this)) {
            islandMap.replaceEntity(start, target, this);
            //System.out.println(this +"moved");
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
        if (shift == 0){
            return start;
        }

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
        int currentPosition;
        int limit;

        switch (direction) {
            case RIGHT:
                currentPosition = this.getCoordinate().getX();
                limit = Settings.islandWidth;
                return currentPosition + shift <= limit;

            case LEFT:
                currentPosition = this.getCoordinate().getX();
                return currentPosition - shift >= 0;

            case UP:
                currentPosition = this.getCoordinate().getY();
                return currentPosition - shift >= 0;

            case DOWN:
                currentPosition = this.getCoordinate().getY();
                limit = Settings.islandHeight;
                return currentPosition + shift <= limit;

            default:
                return false;
        }
    }
    private Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(0, Direction.values().length)];
    }
    private int chooseCoordinateShiftForMove() {
        return ThreadLocalRandom.current().nextInt(0, maxSpeed+1);
    }

    public void die(IslandMap map){
        Cell cell = map.getCell(this.getCoordinate());
        cell.removeEntity(this);
    }
//worker
    public void reduceStarvation(){
        setStarvation(this.starvation - maxFood/6);
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
