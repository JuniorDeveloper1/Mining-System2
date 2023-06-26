package javaproject.rest;


import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Enity.Entity;
import javaproject.game.GameManager;

public class MainForTesting {
	public static Entity gameManager = GameManager.getInstance().getEntity();
	public static void main(String[] args) throws CannotAddEntityException {


		if (gameManager != null) {
			System.out.println("Entity: " + gameManager.getName());
		} else {
			System.out.println("No entity found in the GameManager.");
		}
		
	}

}
