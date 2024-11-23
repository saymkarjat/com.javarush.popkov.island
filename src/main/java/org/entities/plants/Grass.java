package org.entities.plants;

public class Grass extends Plant {

    public Grass() {
        setMaxAmount(100);
        setAmount(getMaxAmount());
    }

    @Override
    public String toString() {
        return "\uD83C\uDF40";
    }
}
