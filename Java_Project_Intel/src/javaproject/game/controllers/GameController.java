package javaproject.game.controllers;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javaproject.game.Enity.Anchors;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;

import java.io.IOException;

public class GameController {
    public Text nameHero;
    
    public Text currentHP;
    public GameManager gameManager = GameManager.getInstance();

    public Button fight;
    public Button inventory;
    public Button shop;

    public Button turn;
    public Text credits;
    public Text currentTurn;

    public Text amountFought;

    public Text capacityLeft;




    public void initialize() {
        nameHero.setText("What do you want to do " + gameManager.getEntity().getName());
        currentHP.setText(Helper.integerToString(gameManager.getEntity().getCurrentHitPoints()));
        credits.setText(Helper.integerToString(gameManager.getPlayerCredits().getCredits()));
        currentTurn.setText(Helper.integerToString(gameManager.getTurn().getCurrentTurn()));
        capacityLeft.setText(Helper.integerToString(Math.round(gameManager.getEntity().capacityLeft())));
        amountFought.setVisible(false);


    }

    public void fight() throws IOException {

        if(gameManager.getAmountFought() < 3){
            this.gameManager.setRunChance(50);
            Helper.closeScene(fight);
            Helper.openScene("fight.fxml", "Fight");
            gameManager.setAmountFought(gameManager.getAmountFought() + 1);
        }else {
            amountFought.setVisible(true);
        }



    }

    public void inventory() throws IOException {
        Helper.closeScene(inventory);
        Helper.openScene("inventory.fxml","Inventory");

        /**
         * Just a test
         */

        for(int i = 0; i < Anchors.values().length; i++) {
            System.out.println(this.gameManager.getEntity().getCarriedItems().toString());
        }
    }

    public void shop() throws IOException {
        /**
         * Add an map called credits. With an OOP class called Credits.
         * The credits will be stored into the gameManager class.
         */
        Helper.closeScene(shop);
        Helper.openScene("shop.fxml", "Shop");


    }

    public void endTurn() throws IOException {
        /**
         * Current problem:
         *  The player can end turns all he wants so he can have infinite money / heal infinite.
         * Possible fix:
         *  A 30% that a monster could attack the Hero. Instad of Hero attacking Monster
         *
         *
         * Every turn 20% of the users hp is added. & Give 10 more credits
         */
        double skipPercentage = 0.3;

        double random = Math.random();


        /**
         * Why the close & open.
         *
         * When clicked on end turn the variables aren't getting updated directly
         * only done when entering the scene.
         */
        gameManager.setAmountFought(0);
        if(random < skipPercentage){
            gameManager.getTurn().nexTurn();
            Helper.closeScene(turn);
            this.gameManager.setRunChance(30);
            Helper.openScene("fight.fxml", "You're attacked!");
        }else {
            gameManager.getTurn().nexTurn();
            Helper.closeScene(turn);
            Helper.openScene("game.fxml", "Game Menu");
        }






    }
}
