package org.entities.plants;


import lombok.Getter;
import lombok.Setter;
import org.entities.Entity;
@Getter
@Setter
public class Plant extends Entity {
    private double amount;
    private double maxAmount;

    public void setAmount(double amount) {

        if (amount >= this.maxAmount) {
            this.amount = this.maxAmount;
        } else if (amount <= 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }

    }
}
