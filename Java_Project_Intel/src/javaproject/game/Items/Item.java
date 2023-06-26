package javaproject.game.Items;

import javafx.scene.image.Image;
import javaproject.game.Enity.Entity;

public abstract class Item {
    private  long indificationNumber;
    private final double weight;
    private Entity owner;

	private int cost;
    

    
    
 	public Item(double weight) {
		 this.setCost(0);
 		if(weight > 0)
 			this.weight = weight;
 		else
 			this.weight = 0;
 		
	}

	public long getIndificationNumber() {
		return indificationNumber;
	}
 	
	public void  setIndificationNumber(long indificationNumber) {
		this.indificationNumber = indificationNumber;
	}
	public double getWeight() {
		return weight;
	}

	public Entity getOwner() {
		return owner;
	}
	public void setOwner(Entity entity) {
		this.owner = entity;
	}

	public boolean hasOwner() {
		return this.getOwner() != null;
	}


	public Image getItemImage() {
		Image image = new Image("items/empty.png");


		if (this instanceof Armor) {
			image = new Image("items/armor_image.png");
		}

		if (this instanceof Backpack) {
			image = new Image("items/backpack_image.png");
		}

		if (this instanceof Weapon) {
			image = new Image("items/weapon_image.png");
		}

		if(this instanceof Potion){
			image = new Image("items/potion_image.png");
		}

		if(image == null) {
			image = new Image("items/empty.png");
		}
		return image;
	}



	public boolean checkImage(Image image) {
		if (this instanceof Backpack) {
			if (getItemImage().getUrl().equals(image.getUrl())) {
				//Item is Backpack & Contains the url
				return true;
			} else {

				//Item is backpack but doesn't have the same url
				return false;
			}
		} else {
			//Item isn't a backpack
			return false;
		}
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
