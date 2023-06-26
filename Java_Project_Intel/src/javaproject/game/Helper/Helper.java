package javaproject.game.Helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javaproject.game.Enity.Anchors;
import javaproject.game.Enity.Entity;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Helper {
	/**
	 * This class exists to provide clarity in classes, if they are not filled with repeating code.
	 * 	These classes will consist of static methods.
	 */
	
	//static long indificationNumber = generateIndificationNumber();
	//static  long indificationWeaponNumber = generateWeaponIndificationNumber();


	
	/**
	 * 
	 * This is the default non edited id generator. 
	 * @return The random number generated.
	 */
	
	public static final long generateIndificationNumber() {
		
		long indificationNumber = 0;
		boolean isCorrect = false;	
		while(!isCorrect) {
			indificationNumber = (long) (Math.random() * Long.MAX_VALUE);
			//Waarom doen we dit. Omdat we een unique number hebben, moeten een random nummer maken bv 12332480707 
			
			if(indificationNumber %2 == 0) {
				isCorrect = true;
				//Hier stopt de while loop met een correcte id.
			}
		}

		return indificationNumber;
	}

	
	
    /**
     * We generate an ID that is even and dividable by 3.
     * Reason here: We use it in the class Weapon & Amor.
     * We generate an unique	 id, the reason why this isn't in item class is because in Amor it shouldn't be dividable by 3
     * 
     * @return the random number that is generated.
     */
	

	public static long generateWeaponIndificationNumber() {
		long indificationWeaponNumber = 0;
		boolean isCorrect = false;	
		
		while(!isCorrect) {
			indificationWeaponNumber = (long) (Math.random() * Long.MAX_VALUE);
			//Waarom doen we dit. Omdat we een unique number hebben, moeten een random nummer maken bv 12332480707 
			
			if(indificationWeaponNumber %2 == 0 && indificationWeaponNumber % 3 == 0) {
				isCorrect = true;
				//Hier stopt de while loop met een correcte id.
			}
			
			if(indificationWeaponNumber < 0) 
				throw new IllegalArgumentException("Your indification number is lower then 0");
		}

		return indificationWeaponNumber;
	}
	
	
	/**Is valid name checker */
	public static boolean isValidHeroName(String name) {
/**
		 ^ matches the start of the string
		[A-Z] matches any uppercase letter
		[a-z]+ matches one or more lowercase letters
		(?:('[a-zA-Z]+)|\s+[A-Z][a-z]+)* matches zero or more occurrences of either an apostrophe followed by one or more letters (both uppercase and lowercase), 
				or one or more whitespace characters followed by an uppercase letter and one or more lowercase letters
		(?:('[a-zA-Z]+)|\s+[A-Z][a-z]+)? matches zero or one occurrences of the same pattern as above
		$ matches the end of the string
*/
		if(name == null || name.length() == 0) 
			//throw new NullPointerException("Je hebt geen naam ingegeven!");
			return false;
		
		if(!(name.matches("^[A-Z][a-z]+(?:('[a-zA-Z]+)|\\s+[A-Z][a-z]+)*(?:('[a-zA-Z]+)|\\s+[A-Z][a-z]+)?$"))) {
			return false;
		}
			//throw new IllegalArgumentException("De naam klopt niet, Kijk voor hoofdletters, dubbele tekens.");
		return true;
	}
	
	public static boolean isValidMonsterName(String name) {
		if(name == null || name.length() == 0) 
			throw new NullPointerException("Je hebt geen naam ingegeven!");
		
		
		if(!(name.matches("\"[A-Z]([a-zA-Z]\\s['])\""))) 	
			throw new IllegalArgumentException("De naam klopt niet");
		
		return true;
	}
	
	
	/**
	 * Calculating total weight of every item.
	 * 
	 * @param items All the items that the user has.
	 * @return total weight
	 */
		public static double calculateTotalWeight(HashMap<Anchors, Item> items) {
			  double totalWeight = 0.0;
			  
			    List<Item> itemList = new ArrayList<>(items.values());	
			    /**
			     * items;values() searched on google. 
			     * 
			     * This will return all the Item objects that are in the items HashMap.
			     */
			for(int i = 0; i < items.size(); i++) {
				Item item = itemList.get(i);
		        totalWeight += item.getWeight(); 
			}
			return totalWeight;
		}
		
		
		
		/**
		 * We generate a random number between 1-3
		 * From least thickness armor to most thickness armor.
		 * 
		 * @return
		 */
		public static final int randomProtectionGenerator() {

	        int randomNumber = (int) (Math.random()*3)+1;
	        int protection = 0;
	        switch (randomNumber) {

	        case 1:
	            protection = (int) (Armor.getMaximumProtection()*0.35);
	            break;

	        case 2:
	            protection = (int) (Armor.getMaximumProtection()*0.65);
	            break;

	        case 3:
	        protection = (int) (Armor.getMaximumProtection());
	        break;

	        }
	        return protection;
	    }


	/**
	 *
	 * @param max The max value that the user can have
	 * @param min The minimum value that the user can have
	 *
	 * Example -> If the user wants a random number between 100-60 then the output should be
	 *            (int) (Math.random() * (100 - 60 + 1)) + 60
	 *            						   max  min 	  min
	 * @return The random number.
	 */
	public static int generateRandomPercentage(int max, int min){
			/*
			(int) (Math.random() * (100 - 60 + 1)) + 60
			 */

		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static String integerToString(int number) {
		return String.valueOf(number);
	}

	public static String integerToString(double number) {
		return String.valueOf(number);
	}

	public static String integerToString(long number) {
		return String.valueOf(number);
	}

	public static String entityToString(Entity number) {
		return String.valueOf(number);
	}


	/**
	 * The logic of this should be:
	 *         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("shop.fxml"));
	 *         Stage stage = new Stage();
	 *         stage.setTitle("Shop");
	 *         stage.setScene(new Scene(root));
	 *         stage.show();
	 * You have to use this for everytime you open a new tab.
	 *
	 * @param fxmlFile This is the file that the user should be directed to.
	 * @param title The title of the page
	 */
	public static void openScene(String fxmlFile, String title) throws IOException {
		Parent root = FXMLLoader.load(Helper.class.getClassLoader().getResource(fxmlFile));
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	}


	/**
	 * The logic of this should be:
	 * 		   Stage currentStage = (Stage) shop.getScene().getWindow();
	 *         currentStage.close();
	 *
	 *
	 *
	 * @param button the button that is clicked on
	 */
	public static void closeScene(Button button){
		Stage currentStage = (Stage) button.getScene().getWindow();
		currentStage.close();
	}



	/**
	 * We create an image view: 50 on 50.
	 *
	 * We give the image variable the item image.
	 *
	 * Then we set the image view image to the giving image Variable
	 *
	 * @param item The item that the user currently has.
	 *
	 * @return ImageView with an image

	public static ImageView createImageView(Item item, int y, int x) {
		ImageView imageView = new ImageView();
		imageView.setY(y);
		imageView.setX(x);
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		Image image = null;

		if(item != null) {
			image = item.getItemImage();
		}else {
			imageView.setImage(new Image("items/empty.png"));
		}

		if (image != null) {
			imageView.setImage(image);
		}



		return imageView;
	}
	 */



	//

}
