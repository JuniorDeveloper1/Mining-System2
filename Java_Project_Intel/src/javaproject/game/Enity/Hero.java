package javaproject.game.Enity;

import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.Item;

import java.util.Scanner;

public class Hero extends Entity{
	public Hero(String name, int protection, int currentHitPoints, double strenght) {
		super(name, protection, currentHitPoints, strenght);
	}
	
	public Hero(String name) {
		super(name);
		this.setName(name);
		this.setProtection(Helper.generateRandomPercentage(10,1));
		this.setCurrentHitPoints(Helper.generateRandomPercentage(200,90));
		this.setMaxHitPoints(this.getCurrentHitPoints());
		this.setStrenght(Helper.generateRandomPercentage(200,50));
	}

	//Weapons

	

	
	public void hit(Entity entity) throws CannotAddEntityException {
		System.out.println("-------");
	    int randomNumber = (int) (Math.random() * 21);
	    this.startFighting();
	    entity.startFighting();



	     if(this.isAlive()||entity.isAlive()) {
	    
		        int weaponDamage = (int) calculateTotalWeaponDamage();
		        int damage = (int) ((this.getStrenght() + weaponDamage - (entity.calculateTotalProtection() - 10)) / 2);
		
		    	if(damage < 0) {
		    		damage = 5;
		    		//To prevent infinite loop for the main.
		    	}
		
		        System.out.println(this.getName() + " has hitted " + entity.getName() + " with {" + damage + "} damage!");
		
		        entity.recieveDamage(damage);
		
		        if (entity.getCurrentHitPoints() <= 0) {
		        	if(!entity.isAlive()) {
		        		//this.collectTreassure(entity);
		        	}
		        	
		        } else {
		            System.out.println(entity.getName() + " has " + entity.getCurrentHitPoints() + " HP remaining.");
		        }
	      }
	}

	//When health is lower then 1 hp deathblow is activated.
	public double  heal() {
		double percentage = Math.random() * 101;
		int hitpointsLeft =(this.getMaxHitPoints() - this.getCurrentHitPoints());
		int healed = (int) (percentage * hitpointsLeft);
		
		if(healed < 0) {
			healed = 0;
		}
		
		if(healed > this.getMaxHitPoints()) {
			healed = this.getMaxHitPoints();
		}
	
		return this.getCurrentHitPoints() + healed;
	}
	
	
	
	public void collectTreassure(Entity entity) throws CannotAddEntityException {
		boolean canAddItem = false;
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i < entity.getCarriedItems().size(); i++) {
            Anchors anchor =  (Anchors) entity.getCarriedItems().keySet().toArray()[i];
            Item item = entity.getCarriedItems().get(anchor);
            
            System.out.println("Item " + item);
            System.out.println("Do you want this item? y/n");
            String choice = scanner.nextLine().toLowerCase();
            
            if(choice.equals("y")) {
            	entity.dropItem(item);
            	pickupItem(item);
            //	System.out.println("You have picked up an item!");
            	canAddItem = true;
            }
            
            if(choice.equals("n")) {
            	System.out.println("You didn't picked the item!");
            }
		}
		
		if(!canAddItem) {
			System.out.println(entity.getName() + " didn't have any items!");
			scanner.close();
		}
		
		
		scanner.close();
	}
	


	public void setName(String name) {
		if(Helper.isValidHeroName(name))
			super.setName(name);
	}
	
	


}
