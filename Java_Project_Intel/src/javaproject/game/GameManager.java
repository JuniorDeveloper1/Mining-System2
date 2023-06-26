package javaproject.game;

import javaproject.game.Credits.Credits;
import javaproject.game.Enity.Entity;
import javaproject.game.Enity.Monster;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Backpack;
import javaproject.game.Items.Weapon;
import javaproject.game.Turn.Turn;

public  class GameManager {
    /**
     * In this class will the player be created / accessed.
     *
     */



    private GameManager() {}
  private Entity entity;


  private Monster monster;

  private Credits playerCredits;
  private Turn turn;
  private int runChance;

  private int totalKilled;



  private boolean itemsCreated = false;
  private static GameManager gameManager;
  private int amountFought = 0;

    public static GameManager getInstance() {
	        if(GameManager.gameManager == null) {
	            GameManager.gameManager = new GameManager();
	            gameManager.setPlayerCredits(new Credits());
	            gameManager.setTurn(new Turn());
	            gameManager.setRunChance(50);
	            gameManager.setItemsCreated(false);
	            gameManager.setAmountFought(0);
	        }
        return GameManager.gameManager;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public Credits getPlayerCredits() {
        return playerCredits;
    }

    private Credits setPlayerCredits(Credits credits){
        return playerCredits = credits;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }


    public int getRunChance() {
        return runChance;
    }

    public void setRunChance(int runChance) {
        this.runChance = runChance;
    }


    public void killedEntity() {
        this.setTotalKilled(this.getTotalKilled()+1);
    }

    public int getTotalKilled() {
        return totalKilled;
    }

    public void setTotalKilled(int totalKilled) {
        this.totalKilled = totalKilled;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean isItemsCreated() {
        return itemsCreated;
    }

    public void setItemsCreated(boolean itemsCreated) {
        this.itemsCreated = itemsCreated;
    }

    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    private Armor armor;

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    private Backpack backpack;

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public int getAmountFought() {
        return amountFought;
    }

    public void setAmountFought(int amountFought) {
        this.amountFought = amountFought;
    }
}
