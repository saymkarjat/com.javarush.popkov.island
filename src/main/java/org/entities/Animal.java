package org.entities;


import lombok.Getter;

public abstract class Animal extends Entity implements Cloneable {
    @Getter
    private int maxSpeed;
    private String name;
    private double weight;
    @Getter
    private int maxAmount;
    @Getter
    private double maxFood;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
