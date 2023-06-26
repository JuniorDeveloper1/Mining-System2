package javaproject.game.Items;


import javaproject.game.Enity.Entity;
import javaproject.game.Helper.Helper;

public class Armor extends Item{
	private int currentProtection;
	private static int MAXIMUM_PROTECTION = (int) (Math.random() * 101)+1;
	
	
	
	
	public Armor(double weight, int currentProtection) {
		super(weight);
		super.setIndificationNumber(Helper.generateIndificationNumber());
		this.currentProtection = currentProtection;
		getIndificationNumber();
	}
	

	
	
	public void repair(int amount) {
		this.setCurrentProtection(this.getCurrentProtection() + amount);
	}
	
	public void takeDamage(int amount) {
		int damageReduction = (int) (Math.random()  * Armor.getMaximumProtection()) +1;
		int actualdamage = amount - damageReduction;
		
		if(actualdamage < 0) 
			actualdamage = 0;
			
		this.setCurrentProtection(this.getCurrentProtection() - amount);
	}

	
	@Override
	public final long getIndificationNumber() {
		return super.getIndificationNumber();
	}
	
	
	
	@Override
	public final double getWeight() {
		return super.getWeight();
	}
	

	public int getCurrentProtection() {
		return currentProtection;
	}


	public void setCurrentProtection(int currentProtection) {
		if(currentProtection < 0) 
			throw new IllegalArgumentException("Your protection is lower then 0");
		
		if(currentProtection > getMaximumProtection())
			throw new IllegalArgumentException("Your currentprotection is highe then the maximum allowed!");
		
		
		this.currentProtection = currentProtection;
	}


	public static int getMaximumProtection() {
		return MAXIMUM_PROTECTION;
	}
	
	
	public Entity getOwner() {
		return super.getOwner();
	}
	public void setOwner(Entity owner) {
		super.setOwner(owner);
	}

	

	/**
	 * Saw this on google.
	 * 
	 * I used this for testing if everything works.
	 */
    @Override
    public String toString() {
        return "Armor{" +
                "identification=" + super.getIndificationNumber() +
                ", weight=" + getWeight() +
                ", capacity=" + this.getCurrentProtection() +
                ", maximum protection=" + this.getMaximumProtection() +
                ", Owner =" + this.getOwner().getName() +
                '}';
    }

	
	

}
