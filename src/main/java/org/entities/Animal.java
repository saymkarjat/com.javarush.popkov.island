package org.entities;


import lombok.Getter;
@Getter
public abstract class Animal extends Entity implements Cloneable {

    private int maxSpeed;
    private String name;
    private double weight;
    private int maxAmount;
    private double maxFood;
    private double starvation;



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
