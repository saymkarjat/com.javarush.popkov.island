package org.entity.plants;

public class Grass extends Plant {

    public Grass() {
        setMaxAmount(100);
        setAmount(getMaxAmount());
    }

    public void grow(){
        setAmount(getAmount()+30);
    }

    @Override
    public String toString() {
        return "\uD83C\uDF40";
    }
}
