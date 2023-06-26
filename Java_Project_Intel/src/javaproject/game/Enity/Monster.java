package javaproject.game.Enity;

import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.*;

import java.util.ArrayList;
import java.util.List;


public class Monster extends Entity {
	private int damage;
	public static int MAXIMUM_DAMAGE;

	public Monster(String name, int currentHitPoints, double strenght, int damage) throws CannotAddEntityException {
		super(name, currentHitPoints, strenght);
		this.damage = damage;
		super.setProtection(Helper.randomProtectionGenerator());
		System.out.println("WORKS 0");

		this.giveRandomItems();

		for (int i = 0; i < Anchors.values().length; i++) {
			System.out.println(this.getCarriedItems().get(Anchors.values()[i]).toString());
		}


		this.setMAXIMUM_DAMAGE(100);
	}


	public Monster(String name) throws CannotAddEntityException {
		super(name);
		super.setMaxHitPoints(100);
		super.setCurrentHitPoints(100);
		super.setProtection(20);
		super.setStrenght(20);
		super.setProtection(Helper.randomProtectionGenerator());
		this.setMAXIMUM_DAMAGE(100);

		this.giveRandomItems();
	}

	public void hit(Entity entity) throws CannotAddEntityException {
		int randomInt = (int) (Math.random() * 100);
		int protection;


		protection = entity.getProtection();

		int damage;
		if (randomInt < this.getCurrentHitPoints()) {
			damage = randomInt;
		} else {
			damage = this.getCurrentHitPoints();
		}


		if (damage >= protection) {
			int totalDamage = (int) (damage + this.calculateTotalStrenght());

			if (totalDamage < entity.getCurrentHitPoints()) {
				entity.setCurrentHitPoints(entity.getCurrentHitPoints() - totalDamage);

				System.out.println(this.getName() + " has hitted " + entity.getName() + " with {" + totalDamage + "} damage!");

				System.out.println(entity.getName() + " has " + entity.getCurrentHitPoints() + " HP remaining.");
			} else {
				entity.setCurrentHitPoints(0);
				this.collectTreassure(entity);

				if(!entity.isAlive()){
					this.deathblow(entity);
				}
			}
		} else {
			System.out.println(this.getName() + " has missed! ");
		}

	}

	public void deathblow(Entity entity) throws CannotAddEntityException {
		System.out.println("Deathblow has activated! healt:" + heal());
		int health = (int) heal();

		if(health > entity.getMaxHitPoints()) {
			health = entity.getMaxHitPoints();
		}

		entity.setCurrentHitPoints((int) health);
		System.out.println(entity.getName() + " is still alive.");

	}
	public double calculateTotalStrenght() {
		return (this.getStrenght() - 5) / 3;
	}

	/**
	 * We used the itemList so we can remove all the items from the other user.
	 *
	 * @param entity represents the other user where currentHitpoints is below 0.
	 * @throws CannotAddEntityException selfmade exception. Original use is in putItemInBag.
	 */
	public void collectTreassure(Entity entity) throws CannotAddEntityException {
		if (!entity.getCarriedItems().isEmpty()) {
			System.out.println("Monster collecting items from: " + entity.getName());
			List<Item> itemList = new ArrayList<>(entity.getCarriedItems().values());
			//Values represents every Item that is in the HashMap that has an Item object.
			int randomIndex = (int) (Math.random() * itemList.size());
			Item item = itemList.remove(randomIndex);


			if (item != null) {
				dropEntityItems(entity, itemList);
				item.setOwner(null);
				if(!(item instanceof Backpack)){
					this.pickupItem(item);
				}
			}
		} else {
			System.out.println(entity.getName() + " has no items!");
		}
	}


	/**
	 * Drop items is meant for to drop every item that the hero has. If you die you should lose every item
	 * If the backpack can't be dropped we could use an item instanceof backpack.
	 * <p>
	 * You have to give an ArrayList because if you only grab an random index in collectreasures you will
	 * get an null pointer.
	 *
	 * @param entity   -> Entity presents the user where the currentHitpoints is below 0.
	 * @param itemList -> The item list is the list of items that the user has in possession.
	 */
	public void dropEntityItems(Entity entity, List<Item> itemList) {
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			if (item != null) {
				entity.dropItem(item);
				item.setOwner(null);
			}
		}
	}

	public void setName(String name) {
		if (Helper.isValidMonsterName(name))
			super.setName(name);
	}

	public String getName() {
		return super.getName();
	}


	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		if ((damage < 1 || damage > 100))
			throw new IllegalArgumentException("Overflow or Underflow of your damage");

		if (damage % 7 != 0)
			throw new IllegalArgumentException("Your Damage has to be devidable by 7");

		this.setDamage(damage);
	}

	public int getMAXIMUM_DAMAGE() {
		return MAXIMUM_DAMAGE;
	}

	public void setMAXIMUM_DAMAGE(int mAXIMUM_DAMAGE) {
		MAXIMUM_DAMAGE = mAXIMUM_DAMAGE;
	}


	private void giveRandomItems() throws CannotAddEntityException {
		Anchors[] anchors = {Anchors.LEFT_HAND, Anchors.RIGHT_HAND, Anchors.BODY, Anchors.BACK};

		// Create unique random items
		Weapon weapon = new Weapon(Helper.generateRandomPercentage(20, 10), Helper.generateRandomPercentage(50, 20));
		Armor armor = new Armor(Helper.generateRandomPercentage(20, 10), Helper.generateRandomPercentage(150, 20));
		Potion potion = new Potion(Helper.generateRandomPercentage(5,1), Helper.generateRandomPercentage(150,20));


		for (int i = 0; i < anchors.length; i++) {
			if (anchors[i] == Anchors.LEFT_HAND || anchors[i] == Anchors.RIGHT_HAND) {

				if(weapon.hasOwner()) {

					weapon = new Weapon(Helper.generateRandomPercentage(20,10),Helper.generateRandomPercentage(150,20));
				}
				
				this.pickupItem(weapon);


			} else if (anchors[i] == Anchors.BODY) {
				if(armor.hasOwner()) {
					armor = new Armor(Helper.generateRandomPercentage(20,10),Helper.generateRandomPercentage(50,20));
				}

				this.pickupItem(armor);

			} else if (anchors[i] == Anchors.BACK) {
				if(potion.hasOwner()){
					potion = new Potion(Helper.generateRandomPercentage(5,1), Helper.generateRandomPercentage(170,5));
				}

				this.pickupItem(potion);

			}
		}

	}


	private boolean shouldSkipAnchor() {
		/**
		 * This is created for the monster
		 * There is an 30% chance that the anchor is skipped so it will not be filled with random items.
		 *
		 */
		double skipPercentage = 0.3;

		double random = Math.random();

		return random < skipPercentage;
	}
}
