package javaproject.game.Items;

import javaproject.game.Helper.Helper;

public class Potion extends Item{

    private int healthAmount;
    public Potion(double weight, int healthAmount) {
        super(weight);
        this.healthAmount = healthAmount;
    }

    public Potion(){
        super(Helper.generateRandomPercentage(4,1));
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }


    /**
     * Saw this on google.
     *
     * I used this for testing if everything works.
     */
    @Override
    public String toString() {
        return "Potion{" +
                "identification=" + super.getIndificationNumber() +
                ", weight=" + getWeight() +
                ", Owner=" + this.getOwner().getName() +
                ", Health=" + this.getHealthAmount() +

                '}';
    }
}
