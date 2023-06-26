package javaproject.game.Items;

import javaproject.exceptions.CannotAddEntityException;
import javaproject.exceptions.ItemNotFoundException;
import javaproject.game.Helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class Backpack extends Item{

	private List<Item> inventory;
	   private final double capacity;
	

	public Backpack(double weight, double capacity) {
		super(weight);

		this.setIndificationNumber(Helper.generateWeaponIndificationNumber());
		
		this.capacity = capacity;

		inventory = new ArrayList<Item>();
	}	
	
		
	
	public boolean isCorrectItem(Item item) throws CannotAddEntityException {
		
	    if(this.getTotalWeight() + item.getWeight() > this.getCapacity()) {
	        throw new IllegalArgumentException("You have too much capacity!");
	    }
	    
	    
		//You cannot add an item if it is an Hero or an entity: Maybe do it like this?:
	    if(!(item instanceof Armor || item instanceof Weapon || item instanceof Backpack || item instanceof Potion)) {
	    	throw new CannotAddEntityException("You cannot add an Entity to your backpack");
	    }
	    
	    return true;
	}

	public void addItem(Item item) throws CannotAddEntityException {			
		if(isCorrectItem(item))
			if(!(this.getInventory().size() > 4)) {
				this.getInventory().add(item);
			}
		
	}
	
	public void removeItem(Item item) throws ItemNotFoundException {
		if(this.getInventory().contains(item))  {
			this.getInventory().remove(item);
		}else {
			throw new ItemNotFoundException("The item cannot be removed because it is not found!");
		}
	}
	
	
	public boolean containsItem(long indificationNumber) {
		for(int i = 0; i < this.getInventory().size(); i++) {
			Item item = this.getInventory().get(i);
			if(item.getIndificationNumber() == indificationNumber) {
				return true;
			}
		}
		
		return false;	
	}
	
	
	@Override
	public double getWeight() {
		return super.getWeight();
		
	}
	
	public int getTotalWeight() {
		int weight = 0;
		for(int i = 0; i < this.getInventory().size(); i++) {
			Item item = this.getInventory().get(i);
			weight += item.getWeight();
		}
		return weight;
	}
	
	@Override
	public long getIndificationNumber() {
		super.setIndificationNumber(Helper.generateIndificationNumber());
		return super.getIndificationNumber();
	}
	
	public List<Item> getInventory() {
		return inventory;
	}




	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}




	public double getCapacity() {
		return capacity;
	}




	/**
	 * Saw this on google.
	 * 
	 * I used this for testing if everything works.
	 */
    @Override
    public String toString() {
        return "Backpack{" +
                "identification=" + super.getIndificationNumber() +
                ", weight=" + getWeight() +
                ", capacity=" + this.getCapacity() +
                ", totalWeight=" + getTotalWeight() +
                ", Owner =" + this.getOwner().getName() +
                
                '}';
    }
	
	

}
