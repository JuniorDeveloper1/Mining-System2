package javaproject.game.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Credits.Credits;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Backpack;
import javaproject.game.Items.Weapon;
import javaproject.game.Turn.Turn;

import java.io.IOException;

public class ShopController {
    public GameManager gameManager = GameManager.getInstance();
    public Button goBack;
    public Button weaponButton;

    public ImageView weaponImage;
    private Weapon weapon;

    public Text weaponID;
    public Text weaponWeight;

    public Text weaponDamage;

    public Text weaponCost;

    private Armor armor;

    public Text armorID;
    public Text armorWeight;

    public Text armorProtection;

    public Text armorCost;
    public Button armorButton;

    public ImageView armorImage;

    private Backpack backpack;

    public Text backpackID;
    public Text backpackWeight;

    public Text backpackCapacity;

    public Text backpackCost;
    public Button backpackButton;

    public ImageView backpackImage;

    private Credits playerCredits = gameManager.getPlayerCredits();
    private Turn turn = gameManager.getTurn();
    
    public Text notEnoughCoins;
    
    private Timeline timeline;




    public void initialize() {

        createItems();
        this.playNotEnoughCoins();
        notEnoughCoins.setVisible(false);


    }

    public void goBack() throws IOException {
        Helper.closeScene(goBack);
        Helper.openScene("game.fxml", "Game");
    }

    private void createItems() {
            createWeapon();
            createArmor();
            createBackpack();
    }

    private void createWeapon() {
        if(!gameManager.isItemsCreated()){
            double weight = Helper.generateRandomPercentage(40,10);
            int damage = Helper.generateRandomPercentage(200,10);
            int cost = Helper.generateRandomPercentage(500,50);

            weapon = new Weapon(weight,damage);
            weapon.setCost(cost);
            gameManager.setWeapon(weapon);
            System.out.println("Item created!");


        }

        Weapon item = gameManager.getWeapon();




        weaponImage.setImage(item.getItemImage());
        weaponCost.setText(Helper.integerToString(item.getCost()));
        weaponID.setText(Helper.integerToString(item.getIndificationNumber()));
        weaponWeight.setText(Helper.integerToString(item.getWeight()));
        weaponDamage.setText(Helper.integerToString(item.getDamage()));
    }

    private void createArmor(){


        if(!gameManager.isItemsCreated()) {
            double weight = Helper.generateRandomPercentage(40, 10);
            int protection = Helper.generateRandomPercentage(350, 50);
            int cost = Helper.generateRandomPercentage(500,100);

            armor = new Armor(weight, protection);
            armor.setCost(cost);
            gameManager.setArmor(armor);
        }

        Armor item = gameManager.getArmor();



        armorImage.setImage(item.getItemImage());
        armorCost.setText(Helper.integerToString(item.getCost()));
        armorID.setText(Helper.integerToString(item.getIndificationNumber()));
        armorWeight.setText(Helper.integerToString(item.getWeight()));
        armorProtection.setText(Helper.integerToString(item.getCurrentProtection()));
    }


    private void createBackpack(){
        if(!gameManager.isItemsCreated()) {
            double weight = Helper.generateRandomPercentage(40, 10);
            int capacity = Helper.generateRandomPercentage(500, 100);
            int cost = Helper.generateRandomPercentage(1000, 500);


            backpack = new Backpack(weight, capacity);
            backpack.setCost(cost);
            gameManager.setBackpack(backpack);
        }

        Backpack item = gameManager.getBackpack();



        backpackImage.setImage(item.getItemImage());
        backpackCost.setText(Helper.integerToString(item.getCost()));
        backpackID.setText(Helper.integerToString(item.getIndificationNumber()));
        backpackWeight.setText(Helper.integerToString(item.getWeight()));
        backpackCapacity.setText(Helper.integerToString(item.getCapacity()));
        gameManager.setItemsCreated(true);
    }

    public void weaponBuy() throws CannotAddEntityException {
        int cost = Integer.parseInt(weaponCost.getText());
        if((playerCredits.getCredits()- cost) > 0){
            weaponButton.setVisible(false);
            gameManager.getEntity().pickupItem(gameManager.getWeapon());
            weaponImage.setImage(new Image("items/empty.png"));
            playerCredits.withdrawCredits(cost);

            hideWeapon();
        }else{
            System.out.println("You don't have enough credits!");
            timeline.playFromStart();
        }

    }

    public void armorBuy() throws CannotAddEntityException {
        int cost = Integer.parseInt(armorCost.getText());
        if((playerCredits.getCredits() - cost) > 0){
            armorButton.setVisible(false);
            gameManager.getEntity().pickupItem(gameManager.getArmor());
            armorImage.setImage(new Image("items/empty.png"));
            playerCredits.withdrawCredits(cost);

            hideArmor();
        }else {
            System.out.println("You don't have enough coins!");
            timeline.playFromStart();
        }
    }

    public void backpackBuy() throws CannotAddEntityException {
        int cost = Integer.parseInt(armorCost.getText());
        if((playerCredits.getCredits()-cost) > 0){

            gameManager.getEntity().pickupItem(gameManager.getBackpack());
            backpackImage.setImage(new Image("items/empty.png"));
            playerCredits.withdrawCredits(cost);

            hideBackpack();

        }else {
            System.out.println("You don't have enough credits!");
            timeline.playFromStart();
        }

    }
    
    private void playNotEnoughCoins() {
    	if(!notEnoughCoins.isVisible()) {
    		timeline = new Timeline(
	    				new KeyFrame(Duration.seconds(0),event -> {
	    					notEnoughCoins.setVisible(true);
	    				}),
	    				new KeyFrame(Duration.seconds(2), event -> {
	    					if(notEnoughCoins.isVisible()) {
	    						notEnoughCoins.setVisible(false);
	    					
	    					}
	    				})
    				);
    		
    		timeline.setCycleCount(1);
    	}
    }


    private void hideBackpack(){
        backpackCapacity.setVisible(false);
        backpackID.setVisible(false);
        backpackWeight.setVisible(false);
        backpackButton.setVisible(false);
        backpackCost.setVisible(false);
    }

    private void hideWeapon(){
        weaponDamage.setVisible(false);
        weaponID.setVisible(false);
        weaponWeight.setVisible(false);
        weaponButton.setVisible(false);
        weaponCost.setVisible(false);
    }

    private void hideArmor(){
        armorProtection.setVisible(false);
        armorID.setVisible(false);
        armorWeight.setVisible(false);
        armorButton.setVisible(false);
        armorCost.setVisible(false);
    }
}
