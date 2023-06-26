package javaproject.game.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Enity.Anchors;
import javaproject.game.Enity.Hero;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Backpack;
import javaproject.game.Items.Potion;
import javaproject.game.Items.Weapon;

import java.io.IOException;


public class MainMenuController {
    public TextField nameInput;
    public GameManager gameManager = GameManager.getInstance();
    public Button play;
    private Hero hero;
    public Text validName;
    public Text validName1;
    
    private Timeline timeline;
    private Timeline timeline2;


    public void play() throws IOException, CannotAddEntityException {
        String name = nameInput.getText().toString();
        createHero(name);
        this.openMainMenu();
        if(!Helper.isValidHeroName(name)) {
            timeline.playFromStart();
        }else {
        	 timeline2.playFromStart();
        }

        
        


    }

    public void exit() {
        Platform.exit();
    }

    private void createHero(String name) throws CannotAddEntityException {

        //Make a name like Johnson.
        if(Helper.isValidHeroName(name)) {
          	hero = new Hero(name);
            System.out.println(hero.getName());
            System.out.println(hero.getStrenght());
            System.out.println(hero.getProtection());
            gameManager.setEntity(hero);
            validName1.setVisible(true);
            validName.setVisible(false);


            //Weapon weapon = new Weapon(20);
            //Armor armor = new Armor(20,40);
            Backpack backpack = new Backpack(40,20);
            //Weapon weapon2 = new Weapon(40,20);
            //Weapon weapon5 = new Weapon(0,20);
            //gameManager.getEntity().pickupItem(weapon);
           //gameManager.getEntity().pickupItem(armor);
            gameManager.getEntity().pickupItem(backpack);
           //gameManager.getEntity().pickupItem(weapon2);
            //gameManager.getEntity().pickupItem(weapon5);
            //hero.pickupItem(weapon);

            Potion potion = new Potion(1,40);
            gameManager.getEntity().pickupItem(potion);

            for(int i = 0; i < Anchors.values().length; i++) {
                System.out.println(hero.getCarriedItems().get(Anchors.values()[i]));
            }
        }else {
        	timeline = new Timeline(
        			new KeyFrame(Duration.seconds(0), event -> {
        				validName.setVisible(true);
        			     validName1.setVisible(false);
        			}),
        			new KeyFrame(Duration.seconds(2), event -> {
        				validName.setVisible(false);
        			     validName1.setVisible(false);
        			}));
        }
        
  
    }
    
    private void openMainMenu() throws IOException {
    	validName1.setVisible(true);
    	timeline2 =  new Timeline
    			(new KeyFrame(Duration.seconds(2), event -> {
    		        Helper.closeScene(play);
    		        try {
						Helper.openScene("game.fxml", "Space Monster");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			})
    	);

    }



}
