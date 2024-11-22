package org.entities;


public abstract class Animal extends Entity implements Cloneable {
    private int maxSpeed;
    private String name;
    private double weight;
    private int maxAmount;
    private double maxFood;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
