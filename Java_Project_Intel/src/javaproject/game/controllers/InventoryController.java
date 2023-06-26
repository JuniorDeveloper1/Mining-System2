package javaproject.game.controllers;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Enity.Anchors;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.*;

import java.io.IOException;
import java.util.List;

public class InventoryController {
    GameManager gameManager = GameManager.getInstance();

    public Button goBack;
    public ImageView item1;
    public ImageView item2;
    public ImageView item3;
    public ImageView item4;

    public ImageView inventory1;
    public ImageView inventory2;
    public ImageView inventory3;
    public ImageView inventory4;

    public ImageView kruisje1;

    public ImageView kruisje2;

    public ImageView kruisje3;

    public ImageView kruisje4;

    public Text id;

    public Text weight;

    public Text owner;

    public Text special;

    public Text potionText;
    public Text potionAmount;


    public void initialize() {
        item1.setImage(setAnchorDisplayItem(0));
        item2.setImage(setAnchorDisplayItem(1));
        item3.setImage(setAnchorDisplayItem(2));
        item4.setImage(setAnchorDisplayItem(3));

        inventory1.setImage(setInventoryDisplayItem(0));
        inventory2.setImage(setInventoryDisplayItem(1));
        inventory3.setImage(setInventoryDisplayItem(2));
        inventory4.setImage(setInventoryDisplayItem(3));

        checkIfBackpack(item1, 0);
        checkIfBackpack(item2, 1);
        checkIfBackpack(item3, 2);
        checkIfBackpack(item4, 3);

        hideDelete();

        clickItem1();
        clickItem2();
        clickItem3();
        clickItem4();
        clickInventory1();
        clickInventory2();
        clickInventory3();
        clickInventory4();
        hoverInventory1();
        hoverInventory2();
        hoverInventory3();
        hoverInventory4();
        

        potionAmount.setVisible(false);
        potionText.setVisible(false);
    }
    public void goBack() throws IOException {
        Helper.closeScene(goBack);
        Helper.openScene("game.fxml", "Game Menu");
    }

    private Image setAnchorDisplayItem(int index) {
        Anchors[] anchors = Anchors.values();
        if (index >= 0 && index < anchors.length) {
            Item anchor = gameManager.getEntity().getCarriedItems().get(anchors[index]);
            if (anchor != null) {
                return anchor.getItemImage();
            }else {
                return new Image("items/empty.png");
            }
        }
        // Return a default image or handle the null case as per your requirement
        return null;
    }

    private Image setInventoryDisplayItem(int index) {
        Image image= new Image("items/empty.png");
        if (gameManager.getEntity().hasBackpack()) {
            Backpack backpack = gameManager.getEntity().backpackExist();
            if (backpack != null && index >= 0 && index < backpack.getInventory().size()) {
                Item item = backpack.getInventory().get(index);
                if (item != null) {
                    image = item.getItemImage();
                }
            }
        }
        return image;
    }

    boolean isShowingInventory = false;
    private void checkIfBackpack(ImageView imageview, int index){
        if(gameManager.getEntity().hasBackpack()) {

            imageview.setOnMouseClicked(event -> {
                Image image = imageview.getImage();
                Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[index]);

                if(item != null) {
                    boolean isBackpack = item.checkImage(image);
                    System.out.println("Is backpack");
                    if (isBackpack) {
                        if(!isShowingInventory){
                            showInventory();
                            System.out.println("Opening backpack..");
                            isShowingInventory = true;
                        }else {
                            hideInventory();
                            isShowingInventory = false;
                        }

                    }
                }
            });
        }
    }
    private void showInventory(){
        inventory1.setVisible(true);
        inventory2.setVisible(true);
        inventory3.setVisible(true);
        inventory4.setVisible(true);
    }

    private void hideInventory(){
        inventory1.setVisible(false);
        inventory2.setVisible(false);
        inventory3.setVisible(false);
        inventory4.setVisible(false);
    }
    private void hideDelete() {
        kruisje1.setVisible(false);
        kruisje2.setVisible(false);
        kruisje3.setVisible(false);
        kruisje4.setVisible(false);
    }


    private void clickItem1() {
        Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[0]);
        if (item != null && !(item instanceof Backpack)) {
            item1.setOnMouseClicked(event -> {
                if (!(item instanceof Potion)) {
                    if (!kruisje1.isVisible()) {
                        kruisje1.setVisible(true);
                    } else {
                        kruisje1.setVisible(false);
                    }
                } else {
                    potionText.setVisible(true);
                    potionAmount.setVisible(true);
                    double hp = ((Potion) item).getHealthAmount();

                    potionAmount.setText(hp + " hp");
                    System.out.println("Healed Before " + gameManager.getEntity().getCurrentHitPoints());
                    gameManager.getEntity().heal(hp);
                    System.out.println("Healed for " + gameManager.getEntity().getCurrentHitPoints());
                    deleteItem(item1,0);
                }
                showWeaponStats(item);
                showAmorStats(item);
                showBackpackStats(item);

                kruisje1.setOnMouseClicked(events -> {
                    deleteItem(item1, 0);
                    System.out.println("Deleted");
                });
            });
        }
    }

    private void clickItem2() {
        Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[1]);
        if (item != null && !(item instanceof Backpack)) {
            item2.setOnMouseClicked(event -> {
                if (!(item instanceof Potion)) {
                    if (!kruisje2.isVisible()) {
                        kruisje2.setVisible(true);
                    } else {
                        kruisje2.setVisible(false);
                    }
                } else {
                    potionText.setVisible(true);
                    potionAmount.setVisible(true);
                    double hp = ((Potion) item).getHealthAmount();

                    potionAmount.setText(hp + " hp");
                    System.out.println("Healed Before " + gameManager.getEntity().getCurrentHitPoints());
                    gameManager.getEntity().heal(hp);
                    System.out.println("Healed for " + gameManager.getEntity().getCurrentHitPoints());
                    deleteItem(item2,1);
                }
                showWeaponStats(item);
                showAmorStats(item);
                showBackpackStats(item);

                kruisje2.setOnMouseClicked(events -> {
                    deleteItem(item2, 1);
                    System.out.println("Deleted");
                });
            });
        }
    }

    private void clickItem3() {
        Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[2]);
        if (item != null && !(item instanceof Backpack)) {
            item3.setOnMouseClicked(event -> {
                if (!(item instanceof Potion)) {
                    if (!kruisje3.isVisible()) {
                        kruisje3.setVisible(true);
                    } else {
                        kruisje3.setVisible(false);
                    }
                } else {
                    potionText.setVisible(true);
                    potionAmount.setVisible(true);
                    double hp = ((Potion) item).getHealthAmount();

                    potionAmount.setText(hp + " hp");
                    System.out.println("Healed Before " + gameManager.getEntity().getCurrentHitPoints());
                    gameManager.getEntity().heal(hp);
                    System.out.println("Healed for " + gameManager.getEntity().getCurrentHitPoints());
                    deleteItem(item3,2);
                }
                showWeaponStats(item);
                showAmorStats(item);
                showBackpackStats(item);

                kruisje3.setOnMouseClicked(events -> {
                    deleteItem(item3, 2);
                    System.out.println("Deleted");
                });
            });
        }
    }

    private void clickItem4() {
        Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[3]);
        if (item != null && !(item instanceof Backpack)) {
            item4.setOnMouseClicked(event -> {
                if (!(item instanceof Potion)) {
                    if (!kruisje4.isVisible()) {
                        kruisje4.setVisible(true);
                    } else {
                        kruisje4.setVisible(false);
                    }
                } else {
                    potionText.setVisible(true);
                    potionAmount.setVisible(true);
                    double hp = ((Potion) item).getHealthAmount();

                    potionAmount.setText(hp + " hp");
                    System.out.println("Healed Before " + gameManager.getEntity().getCurrentHitPoints());
                    gameManager.getEntity().heal(hp);
                    System.out.println("Healed for " + gameManager.getEntity().getCurrentHitPoints());
                    deleteItem(item4,3);
                }
                showWeaponStats(item);
                showAmorStats(item);
                showBackpackStats(item);

                kruisje4.setOnMouseClicked(events -> {
                    deleteItem(item4, 3);
                    System.out.println("Deleted");
                });
            });
        }
    }

    private void clickInventory1() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory1.setOnMouseClicked(event -> {
                List<Item> inventory =gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if(inventory.size() > 0){
                    item = inventory.get(0);
                }
                if (item != null) {
                    try {
                        gameManager.getEntity().dropItem(item);
                        inventory1.setImage(new Image("items/empty.png"));
                        if(item instanceof Potion){
                            potionText.setVisible(true);
                            potionAmount.setVisible(true);
                            double hp = ((Potion) item).getHealthAmount();
                            potionAmount.setText(hp + " hp");
                            gameManager.getEntity().heal(hp);
                            if(item.hasOwner()){
                                gameManager.getEntity().dropItem(item);
                            }
                        }else {
                            gameManager.getEntity().pickupItem(item);
                        }
                        if(findAvailableImageView() != null){
                            findAvailableImageView().setImage(item.getItemImage());
                        }
                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void clickInventory2() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory2.setOnMouseClicked(event -> {
                List<Item> inventory =gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if(inventory.size() > 1){
                    item = inventory.get(1);
                }
                if (item != null) {
                    try {
                        gameManager.getEntity().dropItem(item);
                        inventory2.setImage(new Image("items/empty.png"));
                        if(item instanceof Potion){
                            potionText.setVisible(true);
                            potionAmount.setVisible(true);
                            double hp = ((Potion) item).getHealthAmount();
                            potionAmount.setText(hp + " hp");
                            gameManager.getEntity().heal(hp);
                            if(item.hasOwner()){
                                gameManager.getEntity().dropItem(item);
                            }

                        }else {
                            gameManager.getEntity().pickupItem(item);
                        }
                        if (findAvailableImageView() != null) {
                            findAvailableImageView().setImage(item.getItemImage());
                        }
                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void clickInventory3() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory3.setOnMouseClicked(event -> {
                List<Item> inventory =gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if(inventory.size() > 2){
                    item = inventory.get(2);
                }
                if (item != null) {
                    try {
                        gameManager.getEntity().dropItem(item);
                        inventory3.setImage(new Image("items/empty.png"));
                        if(item instanceof Potion){
                            potionText.setVisible(true);
                            potionAmount.setVisible(true);
                            double hp = ((Potion) item).getHealthAmount();
                            potionAmount.setText(hp + " hp");
                            gameManager.getEntity().heal(hp);
                            if(item.hasOwner()){
                                gameManager.getEntity().dropItem(item);
                            }
                        }else {
                            gameManager.getEntity().pickupItem(item);
                        }
                        if (findAvailableImageView() != null) {
                            findAvailableImageView().setImage(item.getItemImage());
                        }
                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void clickInventory4() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory4.setOnMouseClicked(event -> {
                List<Item> inventory =gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if(inventory.size() > 3){
                    item = inventory.get(3);
                }
                if (item != null) {
                    try {
                        gameManager.getEntity().dropItem(item);
                        inventory4.setImage(new Image("items/empty.png"));
                        if(item instanceof Potion){
                            potionText.setVisible(true);
                            potionAmount.setVisible(true);
                            double hp = ((Potion) item).getHealthAmount();
                            potionAmount.setText(hp + " hp");
                            gameManager.getEntity().heal(hp);
                            if(item.hasOwner()){
                                gameManager.getEntity().dropItem(item);
                            }
                        }else {
                            gameManager.getEntity().pickupItem(item);
                        }
                        if (findAvailableImageView() != null) {
                            findAvailableImageView().setImage(item.getItemImage());
                        }
                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void hoverInventory1() {
    	  if (gameManager.getEntity().hasBackpack()) {
    		  inventory1.setOnMouseEntered(event -> {
                  List<Item> inventory =gameManager.getEntity().backpackExist().getInventory();
                  Item item = null;

                  if(inventory.size() > 0){
                      item = inventory.get(0);
                  }
    			  
                  if(item != null) {
                      showWeaponStats(item);
                      showAmorStats(item);
                      showBackpackStats(item);
                  }
    		  });
    		  
    		  inventory1.setOnMouseExited(event  -> {
    			  emptyStats();
    		  });
    		  
    		 
    		  
    	  }
    }
    
    private void hoverInventory2() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory2.setOnMouseEntered(event -> {
                List<Item> inventory = gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if (inventory.size() > 1) {
                    item = inventory.get(1);
                }

                if (item != null) {
                    showWeaponStats(item);
                    showAmorStats(item);
                    showBackpackStats(item);
                }
            });
  		  inventory2.setOnMouseExited(event  -> {
			  emptyStats();
		  });
        }
    }

    private void hoverInventory3() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory3.setOnMouseEntered(event -> {
                List<Item> inventory = gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if (inventory.size() > 2) {
                    item = inventory.get(2);
                }

                if (item != null) {
                    showWeaponStats(item);
                    showAmorStats(item);
                    showBackpackStats(item);
                }
            });
            
  		  inventory3.setOnMouseExited(event  -> {
			  emptyStats();
		  });
        }
    }

   private void hoverInventory4() {
        if (gameManager.getEntity().hasBackpack()) {
            inventory4.setOnMouseEntered(event -> {
                List<Item> inventory = gameManager.getEntity().backpackExist().getInventory();
                Item item = null;

                if (inventory.size() > 3) {
                    item = inventory.get(3);
                }

                if (item != null) {
                    showWeaponStats(item);
                    showAmorStats(item);
                    showBackpackStats(item);
                }
            });
            
  		  inventory4.setOnMouseExited(event  -> {
			  emptyStats();
		  });
        }
    }


    private ImageView findAvailableImageView() {
        String emptyImageUrl = new Image("items/empty.png").getUrl();
        if (item1.getImage() != null && item1.getImage().getUrl().equals(emptyImageUrl)) {
            return item1;
        } else if (item2.getImage() != null && item2.getImage().getUrl().equals(emptyImageUrl)) {
            return item2;
        } else if (item3.getImage() != null && item3.getImage().getUrl().equals(emptyImageUrl)) {
            return item3;
        } else if (item4.getImage() != null && item4.getImage().getUrl().equals(emptyImageUrl)) {
            return item4;
        }
        return null;
    }





    private void deleteItem(ImageView imageView, int index){
        Item item = gameManager.getEntity().getCarriedItems().get(Anchors.values()[index]);
        if(item != null) {
            gameManager.getEntity().dropItem(item);
        }
        imageView.setImage(new Image("items/empty.png"));
    }

    private void showItemStats(Item item) {
        id.setText(Helper.integerToString(item.getIndificationNumber()));
        weight.setText(Helper.integerToString(item.getWeight()));
        if (item.getOwner() != null) {
            owner.setText(item.getOwner().getName());
        } else {
            owner.setText("");
        }
        
        if(item instanceof Potion) {
        	double  healed= ((Potion) item).getHealthAmount();
        	special.setText(Helper.integerToString(healed));
        	
        }
    }
    
    private void emptyStats() {
    	id.setText("");
    	weight.setText("");
    	owner.setText("");
    	special.setText("");
    }

    private void showWeaponStats(Item item) {
        if (item instanceof Weapon) {
            showItemStats(item);
            double damage1 = ((Weapon) item).getDamage();
            special.setText(Helper.integerToString(damage1));
        }
    }

    private void showAmorStats(Item item) {
        if (item instanceof Armor) {
            id.setText(Helper.integerToString(item.getIndificationNumber()));
            weight.setText(Helper.integerToString(item.getWeight()));
            if (item.getOwner() != null) {
                owner.setText(item.getOwner().getName());
            } else {
                owner.setText("");
            }

            double protection = ((Armor) item).getCurrentProtection();
            special.setText(Helper.integerToString(protection));
        }
    }

    private void showBackpackStats(Item item) {
        if (item instanceof Backpack) {
            id.setText(Helper.integerToString(item.getIndificationNumber()));
            weight.setText(Helper.integerToString(item.getWeight()));
            if (item.getOwner() != null) {
                owner.setText(item.getOwner().getName());
            } else {
                owner.setText("");
            }

            double capacity = ((Backpack) item).getCapacity();
            special.setText(Helper.integerToString(capacity));
        }
    }

}
