package javaproject.game.Enity;

import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.*;

import java.util.HashMap;

public abstract class Entity {
   private String name;
   private int protection;
   private int maxHitPoints;
   private int currentHitPoints;
   private static final double AVARAGE_STRENGHT = 10;
   private static final int DEFAULT_PROTECTION = 10;
   private boolean isFighting;
   private double strenght;

   private int totalArmor = 0;

   private boolean isRoundedToPrime;
   
	private HashMap<Anchors, Item> carriedItems;
   /**
    * Do something with the Anchors?
    */
   
   
	public Entity(String name, int protection, int currentHitPoints, double strenght) {
	super();
	this.carriedItems = new HashMap<Anchors, Item>();
	this.name = name;
	this.protection = protection;
	this.currentHitPoints = currentHitPoints;
	this.strenght = strenght;
	
	this.setMaxHitPoints(150);
	}
	
	
	public Entity(String name, int currentHitPoints, double strenght) {
		super();
		this.carriedItems = new HashMap<Anchors, Item>();
		this.name = name;
		this.currentHitPoints = currentHitPoints;
		this.setProtection(6);
		this.strenght = strenght;

		this.setMaxHitPoints(150);
	}
	
	public Entity(String name) {
		this.name = name;
		this.setMaxHitPoints(200);
		this.setCurrentHitPoints(150);
		this.setProtection(2);
		this.setStrenght(40);
		this.carriedItems = new HashMap<Anchors, Item>();
		
		this.setMaxHitPoints(150);
	}



    public void putItemInSlot(Item item) {
		boolean isNotAvailable = false;

		for(int i =0; i < Anchors.values().length; i++) {
			if((this.getCarriedItems().get(Anchors.values()[i]) == null)) {
				this.getCarriedItems().put(Anchors.values()[i], item);
				isNotAvailable  = true;
				break;
			}			
		}
		
		if(isNotAvailable == false) 
			throw new NullPointerException("You don't have any slots left;"); 
	}
	
	public void putItemInBag(Item item) throws CannotAddEntityException {
		Backpack backpack = null;
		
		for(int i = 0; i < Anchors.values().length; i++) {	
			//We zetten de item van de anchor naar de backpack
			if (this.getCarriedItems().get(Anchors.values()[i]) instanceof Backpack) {
				backpack = (Backpack) (this.getCarriedItems().get(Anchors.values()[i]));
				// Item is in backpack. Dus zet je de value niet meer naar null.
				break;
			}
		}
		
		if(backpack != null) {
			double weightItem = item.getWeight();
			
			if(backpack.getTotalWeight() + weightItem   > backpack.getCapacity()) 
				throw new NullPointerException("Je hebt teveel gewicht!");
				
			
			//Als je een backpack hebt en dat er genoeg gewicht is:
			this.backpackExist().addItem(item);
		}
		
	}
	
	public boolean isPrimeNumber(double hitPoints) {
		for(int i = 2; i <= Math.sqrt(hitPoints); i++) {
			if(hitPoints%i==0) {
				return false;
			}
		}
		return true;
	}
	
	public int setToNearestPrimeNumber(int hitPoints) {
		while(!isPrimeNumber(hitPoints)) {
			hitPoints--;
		}
		return hitPoints;
		/**
		 * Als het geen prime number is gaat het -- doen en blijven checken als het een primenumber is.
		 */
	}
	
	public void equipItem(Item item) throws CannotAddEntityException {
		if(item == null) {
			throw new NullPointerException("Je hebt geen geldigde wapen!");
		}
		
		if(!item.getOwner().equals(this.getName())) 
			throw new IllegalArgumentException("Item does not belong to you");
			
		if(this.hasBackpack()==false)
			throw new IllegalArgumentException("You don't have a bag");
		
		
		this.removeItemFromBackpack(item);
		this.pickupItem(item);
	}
	
	public void unequipItem(Item item) throws CannotAddEntityException {
			if(!item.getOwner().equals(this.getName()))
				throw new IllegalArgumentException("The item doesn't belong to you!");
			
			
			
			for(int i = 0; i < Anchors.values().length; i++) {
				if(item == this.getCarriedItems().get(Anchors.values()[i])) { //Checks if the item is in your anchors.
					this.putItemInBag(item);
					this.removeItemFromItemSlot(item);
				}else {
					throw new IllegalArgumentException("Item is not in your slots!");
				}
					
			}
				
	}	

	public void removeItemFromBackpack(Item item) {
		if(this.hasBackpack() == true) {
				
			if(this.backpackExist().getInventory().contains(item)) {
				this.backpackExist().getInventory().remove(item);
			}
		}
	}
	
	public void removeItemFromItemSlot(Item item) {
		for (int i = 0; i < Anchors.values().length; i++) {
			if (this.getCarriedItems().get( Anchors.values()[i]) == item) {
				this.getCarriedItems().remove(Anchors.values()[i], item);
				break;
			}
		}		
	}
	
	public void dropItem(Item item) {
		if(item == null) 
			throw new NullPointerException("Dit is niet een geldig wapen!");
		
		if(item.getOwner() == null) 
			throw new NullPointerException("The item doesn't have a owner!");
		
		if(this.hasBackpack() == true && this.backpackExist().getInventory().contains(item)) {
			this.removeItemFromBackpack(item);
		}else {
			this.removeItemFromItemSlot(item);
		}
		
		item.setOwner(null);
	}
	
	public void transferItemTo(Entity otherEntity, Item item) throws CannotAddEntityException {
		if(item == null) 
			throw new NullPointerException("Item doesn't exist");
		
		if(item.getOwner() != this) 
			throw new IllegalArgumentException("This is not your item");
		
		if(otherEntity == null)
			throw new NullPointerException("Other entity doesn't exist");
		
		
		if(item instanceof Armor) {
			int armor = this.getTotalArmor() -1;
			this.setTotalArmor(armor);


		}
		
	//item.setOwner(otherEntity);
		
		if(this.hasBackpack() == true && this.backpackExist().getInventory().contains(item)) {
			this.removeItemFromBackpack(item);
		}else {
			this.removeItemFromItemSlot(item);
		}
		
		
		otherEntity.pickupItem(item);
			
		
	}

	public void pickupItem(Item item) throws CannotAddEntityException {

		double itemWeight = item.getWeight();
		double capacity = this.capacityLeft();

		/**
		 * You can only have 2 Armor items.
		 * You can only have 4 weapons.
		 * For armor, it has to come first on body -> back -> right -> left  the  correct order
		 * For a weapon it is right -> left -> body -> back
		 */
		if(item.getOwner() != null)
			throw new NullPointerException("Item already has an owner!");






		if(capacity - itemWeight < 0)
			throw new IllegalArgumentException("You can't add this item! It weighs too much!");



		boolean addItem = false;
		if (item instanceof Armor) {
			for (int i = 0; i < this.getCarriedItems().size(); i++) {
				if (this.getCarriedItems().get(Anchors.values()[i]) instanceof Armor) {
					this.setTotalArmor(this.getTotalArmor()+1);
				}
			}
			if (this.getTotalArmor() >= 2)
				throw new IllegalArgumentException("You have too much armor!");

			//Put item into the bag or item slot.


			Anchors[] anchors = {Anchors.BODY, Anchors.BACK, Anchors.RIGHT_HAND, Anchors.LEFT_HAND};
			for(int i = 0; i < anchors.length; i++) {
				if(this.getCarriedItems().get(anchors[i]) == null) {
					//Als de item vrij is.
					this.getCarriedItems().put(anchors[i], item);
					item.setOwner(this);
					addItem = true;
					break;

				}
			}

			if(!addItem) {
				if(this.hasBackpack()) {
					if(	this.backpackExist().getTotalWeight() + itemWeight   < backpackExist().getCapacity()) {
						this.putItemInBag(item);
						System.out.println("Item added to bag!");
						item.setOwner(this);
						
					}else {
						throw new IllegalArgumentException("Not enough capacity");
					}
				}
			}



		}//End of armor

		if (item instanceof Weapon) {
			Anchors[] anchors = {Anchors.RIGHT_HAND, Anchors.LEFT_HAND, Anchors.BODY, Anchors.BACK};
			for(int i = 0; i < anchors.length; i++) {
				if(this.getCarriedItems().get(anchors[i]) == null) {
					this.getCarriedItems().put(anchors[i], item);
					item.setOwner(this);
					addItem = true;
					break;
				}
			}


			if(!addItem) {
				if(this.hasBackpack()) {
					if(this.backpackExist().getTotalWeight() + itemWeight   < backpackExist().getCapacity()) {
						this.putItemInBag(item);
						System.out.println("Item added to bag!");
						item.setOwner(this);
					}
				}
			}
		}

		if(item instanceof Backpack) {
			this.putItemInSlot(item);
			item.setOwner(this);
		}


		if(item instanceof Potion){
			Anchors[] anchors = {Anchors.RIGHT_HAND, Anchors.LEFT_HAND, Anchors.BODY, Anchors.BACK};
			for(int i = 0; i < anchors.length; i++) {
				if(this.getCarriedItems().get(anchors[i]) == null) {
					this.getCarriedItems().put(anchors[i], item);
					item.setOwner(this);
					addItem = true;
					break;
				}
			}


			if(!addItem) {
				if(this.hasBackpack()) {
					if(this.backpackExist().getTotalWeight() + itemWeight   < backpackExist().getCapacity()) {
						this.putItemInBag(item);
						System.out.println("Item added to bag!");
						item.setOwner(this);
					}
				}
			}

		}
	}

	public void startFighting( ) {
		this.setFighting(true);
	}
	
	public void stopFighting() {
		this.setFighting(false);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getProtection() {
		return protection;
	}
	
	public int calculateTotalProtection() {
		int protections = 0;
		/**
		 * We grab the current protection of the class Armor with a typecast. This allows to calculate the real protection in the setProtection().
		 */
		if(this.getCarriedItems() != null) {
			if(this.getCarriedItems().size() != 0) {
				//If the player doesn't have any armor.
				for(int i = 0; i < Anchors.values().length; i++ ) {
					Item item = this.getCarriedItems().get(Anchors.values()[i]);
					if(item != null) {
						System.out.println("Komt hier");
						if(item instanceof Armor) {
							//System.out.println(protections);
							protections += ((Armor) item).getCurrentProtection();
						}
					}
				}
			}
		}


		return protections;

		
	}
	
	public static int getDefaultProtection() {
		return DEFAULT_PROTECTION;
	}

	public void setProtection(int protection) {
		if(protection <  0) 
			throw new NullPointerException("Your protection is lower then 0");
		  
		  //The users protection is added with the hero's armor protection
		this.protection = protection + calculateTotalProtection();
	}
	
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	public void setMaxHitPoints(int maxHitPoints) {
		if(maxHitPoints <= 0) 
			throw new NullPointerException("Maxhitpoint must be higher then 0");
		
		this.maxHitPoints = maxHitPoints;
	}
	
	public int getCurrentHitPoints() {
		return currentHitPoints;
	}
	
	public void setCurrentHitPoints(int currentHitPoints) {
		if(currentHitPoints < 0)
			this.setCurrentHitPoints(0);
		
		if(currentHitPoints > this.getMaxHitPoints()) 
			this.setCurrentHitPoints(this.getMaxHitPoints());
		
		if(!this.isFighting) {
			this.setToNearestPrimeNumber(currentHitPoints);
		}
		
		this.currentHitPoints = currentHitPoints;
	}
	
	public double getStrenght() {
		return strenght;
	}
	
	public void setStrenght(double strenght) {
		this.strenght = strenght;
	}
	   	
	public static double getAvarageStrenght() {
		return AVARAGE_STRENGHT;
	}

	public boolean isFighting() {
		return isFighting;
	}

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
	}
	
	public boolean isRoundedToPrime() {
		return isRoundedToPrime;
	}

	public void setRoundedToPrime(boolean isRoundedToPrime) {
		this.isRoundedToPrime = isRoundedToPrime;
	}
	
	public HashMap<Anchors, Item> getCarriedItems() {
		return carriedItems;
	}

	
	/**
	 * Checks if the players has an backpack.
	 * If the user has an backpack it sets the value of backpack to the backpack that exist in the hashmap.
	 * 
	 * If the user doesn't have an backpack the value is set to null -> false
	 * Else it is set to true.
	 * @return boolean if the user has an backpack
	 */
	public boolean hasBackpack() {
		Backpack backpack = null;
		for (int i = 0; i < Anchors.values().length; i++) {
			if (this.getCarriedItems().get(Anchors.values()[i]) instanceof Backpack) {
				//Checkt als er een item zit in de carried items hashmap
				 backpack = (Backpack)(this.getCarriedItems().get(Anchors.values()[i]));
				 //Zet de backpack op de item.
				break;
			}
		}
		
		if (backpack==null) {
			return false;
			//Als er niks is veranderd.
		}else {
			return true;
			//Als er werl iets is veranderd;
		}
			
	}
	
	/**
	 * This method creates a variable called backpack and sets it to null.  
	 * When the hashmaps contains an backpack it sets the value into the backpack variable.
	 * 
	 * 
	 * @return return the value of backpack.
	 */
	public Backpack backpackExist() {
		Backpack backpack = null;
		for (int i = 0; i < Anchors.values().length; i++) {
			if (this.getCarriedItems().get(Anchors.values()[i]) instanceof Backpack) {
				 backpack = (Backpack)(this.getCarriedItems().get(Anchors.values()[i]));
				break;
			}
		}
		return backpack;
	}

	public int getTotalArmor() {
		return totalArmor;
	}

	public void setTotalArmor(int totalArmor) {
		this.totalArmor = totalArmor;
	}
	
	//Calculating the total Weapon damage.
	public double calculateTotalWeaponDamage() {
		/**
		 *
		 */
		double weaponDamage = 0;
		for(int i =0; i < this.getCarriedItems().size(); i++) {
			Item item = this.getCarriedItems().get(Anchors.values()[i]);
			if(item instanceof Weapon && (item == this.getCarriedItems()
					.get(Anchors.LEFT_HAND) || item == this.getCarriedItems().get(Anchors.RIGHT_HAND)) ) {
				weaponDamage += ((Weapon) item).getDamage();
				/**Wat is dit. Omdat item een een super klassen van Weapon is kan je een de Klassen Weapon meegeven als item zodat je getDamage kan pakken.
				Dit is een type cast zoals (int) 5.34 -> We maken item Weapon. */	
			}
		}
		return weaponDamage;
		
	}
	
	public   double calculate_capacity(double strength) {
		//115,130,150,175,200,230,260,300,350,400
		double capacity = 0;
		//int[] carrying_capacity = new int[] {115,130,150,175,200,230,260,300,350,400};
		
		if(strength >= 1.00 && strength <= 10.00) {
			double amount = strength * 10;
			capacity =  (int) amount;
		}
		
		if(strength >= 11.00 && strength <= 20.00) {
			int[] carrying_capacity = new int[] {115, 130, 150, 175, 200, 230, 260, 300, 350, 400};
			capacity = carrying_capacity[(int) (strength - 11)];
			/**
			 * Logica:
			 * Je hebt strenght 11, maar moet  115kg hebben (INDEX : 0)
			 * 11 - 11 = index 0
			 * 12 - 11 = index 1
			 * 13 - 11 = index 2
			 */
		}
		
		if(strength >= 21.00) {
			//double amount = strength - 10;
			capacity = 4* calculate_capacity((int) (strength - 10));
		}
	
		
		for(int i = 0; i < Anchors.values().length; i++) {
			if(this.getCarriedItems().get(Anchors.values()[i]) != null ) {
				capacity +=  this.getCarriedItems().get(Anchors.values()[i]).getWeight();
			}
		}
		
		
		if(this.hasBackpack()) {
			for(int i = 0; i < backpackExist().getInventory().size(); i++) {
						capacity += this.backpackExist().getTotalWeight();
			}
		}
		
		if(capacity > 5000) {
			capacity = 4999;
		}
		
		return capacity;
	}
	public boolean isAlive() {
		return this.getCurrentHitPoints() > 0;
	}
	
	public double multiplyStrenght(int amount) {
		return this.getStrenght() * amount;
	}
	
	public double divideStenght(int amount) {
		return this.getStrenght() / amount;	
	}
	
	public void recieveDamage(double damage) {
		this.setCurrentHitPoints((int)(this.getCurrentHitPoints() - damage));
	}

	public void giveHP(int hp) {
		if((hp + this.getCurrentHitPoints()) < getMaxHitPoints()) {
			this.setCurrentHitPoints(this.getCurrentHitPoints() + hp);
		}else {
			this.setCurrentHitPoints(this.getMaxHitPoints());
		}
	}

	public int calculateTotalWeight(HashMap<Anchors, Item> items) {
		return (int) Helper.calculateTotalWeight(items);
	}

	public double calculateEntityHP() {
		double currentHP = this.getCurrentHitPoints();
		double maxHP = this.getMaxHitPoints();
		double progress = currentHP / maxHP;
		return Math.min(progress, 1.0);
	}

	public double  heal() {
		/**
		 * Debuff this
		 */
		double percentage = Math.random() * 20;
		//Before Math.random * 101
		int hitpointsLeft =(this.getCurrentHitPoints());
		//Before: MaxHit - Current
		int healed = (int) (percentage * hitpointsLeft)/100;

		if(healed < 0) {
			healed = 0;
		}

		if(healed > this.getMaxHitPoints()) {
			healed = this.getMaxHitPoints();
		}

		return this.getCurrentHitPoints() + healed;
	}


	public void  heal(double hp) {
		/**
		 * Debuff this
		 */

		double total = this.getCurrentHitPoints() + hp;


		if(total > this.getMaxHitPoints()) {
			total = this.getMaxHitPoints();
		}

		this.setCurrentHitPoints((int) total);
	}

	public double capacityLeft() {
		double currentCapacity = this.calculate_capacity(this.getStrenght());
		int left = 0;
		for(int i =0; i < Anchors.values().length; i++){
			Item anchors =this.getCarriedItems().get(Anchors.values()[i]);
			if( anchors!= null) {
				left += anchors.getWeight();
			}
		}

		return currentCapacity - left;
	}

	public void showItems() {
	    Anchors[] anchors = Anchors.values();
	    int length = anchors.length;
	    
	    for (int i = 0; i < length; i++) {
	        Item item = this.getCarriedItems().get(anchors[i]);
	            System.out.println(i + ":  --------");
	            System.out.println(item);
	            System.out.println(this.getCarriedItems());
	            System.out.println(" -- --------");
	            break; // Stop the loop after printing the first weapon
	        
	    }
	}
}

