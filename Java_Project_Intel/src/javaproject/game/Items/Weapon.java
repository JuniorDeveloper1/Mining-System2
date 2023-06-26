package javaproject.game.Items;


import javaproject.game.Helper.Helper;

public class Weapon extends Item{
	private double damage;
	
	
	public Weapon(double weight, int damage) {
		super(weight);
		super.setIndificationNumber(Helper.generateWeaponIndificationNumber());
		this.damage = damage;
	}
	
	public Weapon(double weight) {
		super(weight);
	}
	 
	public long getIndificationNumber() {

		return super.getIndificationNumber();
	}
	
	public final double getWeight() {
		return super.getWeight();
	}

	
	
	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		if((damage < 1 || damage > 100)) 
			throw new IllegalArgumentException("Overflow or Underflow of damage!");
		
		if(damage % 7 != 0)
			throw new IllegalArgumentException("Your damage isnt divideable by 7");
			
		
		this.damage = damage;
	}


	
	/**
	 * Saw this on google.
	 * 
	 * I used this for testing if everything works.
	 */
    @Override
    public String toString() {
        return "Weapon{" +
                "identification=" + super.getIndificationNumber() +
                ", weight=" + getWeight() +
                ", Owner=" + this.getOwner().getName() +
                ", Damage=" + this.getDamage() +

                '}';
    }
	

}
