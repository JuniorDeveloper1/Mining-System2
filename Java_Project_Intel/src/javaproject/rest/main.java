package javaproject.rest;


import javaproject.game.Enity.Monster;
import javaproject.game.Enity.Hero;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Backpack;
import javaproject.game.Items.*;
import javaproject.exceptions.*;


public class main {
	public static void main(String[] args) throws CannotAddEntityException {
		//super(name, protection, currentHitPoints, strenght);
		Hero hero = new Hero("Rocket", 2, 100,50);

		Weapon weaponHero = new Weapon(2, 10);
		Armor armor = new Armor(30,1000);

		
		Backpack backpack = new Backpack(10, 100);
		
		//hero.pickupItem(weaponHero);
		hero.pickupItem(armor);
		hero.pickupItem(backpack);
		hero.backpackExist().addItem(weaponHero);

		
		
		Weapon weaponMonster = new Weapon(10, 140);
		Armor armor2 = new Armor(30, 10);
	
		 Monster monster = new Monster("Thanos", 100, 700, 0);
		 //String name, int currentHitPoints, double strenght, int damage
		System.out.println("NUMBER WEAPON MONSTER  " + weaponMonster.getIndificationNumber());
		
		System.out.println("**Information  before fight**");
		System.out.println("(Hero) Weight " + hero.getName() + "  :" + hero.calculateTotalWeight(hero.getCarriedItems()));
		System.out.println("(Monster) Weight " + monster.getName() + "  :" + monster.calculateTotalWeight(monster.getCarriedItems()));

		System.out.println("(Hero) Protection " + hero.getName() + "  :" + hero.calculateTotalProtection());
		System.out.println("(Monster) Protection " + monster.getName() + "  :" + monster.calculateTotalProtection());
		System.out.println("--");
		System.out.println("(Hero) Health " + hero.getName() + "  :" +hero.getCurrentHitPoints());
		System.out.println("(Monster) Health " + monster.getName() + "  :" +monster.getCurrentHitPoints());
		System.out.println("**Information before fight**");
		
		
	
		//0 = Monster
		while(hero.isAlive() && monster.isAlive()) {
					hero.hit(monster);
		
					monster.hit(hero);
		}
		
		System.out.println("**Information  after fight**");
		System.out.println("(Hero) Weight " + hero.getName() + "  :" + hero.calculateTotalWeight(hero.getCarriedItems()));
		System.out.println("(Monster) Weight " + monster.getName() + "  :" + monster.calculateTotalWeight(monster.getCarriedItems()));
		System.out.println("--");
		System.out.println("(Hero) Health " + hero.getName() + "  :" +hero.getCurrentHitPoints());
		System.out.println("(Monster) Health " + monster.getName() + "  :" +monster.getCurrentHitPoints());
		System.out.println("**Information  after fight**");

	}
}
