package javaproject.game.Turn;

import javaproject.game.GameManager;

public class Turn {
    /**
     * This is the Turn class.
     *
     * With the turn logic.
     *
     * The user can receive extra money/health with ending the turn.
     *
     * The monster is created in the fight classes.
     *
     * The monster's hp, damage and strenght is based on the turn the player is on.
     *
     */

    //TODO: Add max turn + endscene fxml
    private int currentTurn;

    GameManager gameManager = GameManager.getInstance();

    public Turn() {
        this.setCurrentTurn(1);

    }

    public void nexTurn() {
        this.setCurrentTurn(this.getCurrentTurn() + 1);
        System.out.println("Current turn: " + this.getCurrentTurn());

        /**
         * Add logic for coins and hp
         */
        gameManager.getPlayerCredits().addCredits(20);

        int totalHP = gameManager.getEntity().getCurrentHitPoints();
        //20%
        int percentage = (int) (totalHP * 0.2);

        //Add the current percentage
        gameManager.getEntity().giveHP(percentage);
        gameManager.setItemsCreated(false);


    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    private void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
