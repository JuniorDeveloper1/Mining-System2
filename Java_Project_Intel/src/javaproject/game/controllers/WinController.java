package javaproject.game.controllers;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Enity.Anchors;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.Armor;
import javaproject.game.Items.Backpack;
import javaproject.game.Items.Item;
import javaproject.game.Items.Potion;
import javaproject.game.Items.Weapon;

import java.io.IOException;
import java.util.List;

public class WinController {
    public GameManager gameManager = GameManager.getInstance();
    public ImageView item1;
    public ImageView item2;
    public ImageView item3;
    public ImageView item4;

    public ImageView inventory1;
    public ImageView inventory2;
    public ImageView inventory3;
    public ImageView inventory4;

    public Button save;

    public Button yourInventory;
    
    public Text creditsAmount;
    
    public Text id;
    public Text weight;
    public Text owner;
    public Text special;

    private int amountOfItemsChosen;
   

    public void initialize() throws CannotAddEntityException {
        item1.setImage(setAnchorDisplayItem(0));
        item2.setImage(setAnchorDisplayItem(1));
        item3.setImage(setAnchorDisplayItem(2));
        item4.setImage(setAnchorDisplayItem(3));
        weight.setText("WORKS");
        
        hoverItem1();
        hoverItem2();
        hoverItem3();
        hoverItem4();

        inventory1.setImage(setInventoryDisplayItem(0));
        inventory2.setImage(setInventoryDisplayItem(1));
        inventory3.setImage(setInventoryDisplayItem(2));
        inventory4.setImage(setInventoryDisplayItem(3));

        checkIfBackpack(item1, 0);
        checkIfBackpack(item2, 1);
        checkIfBackpack(item3, 2);
        checkIfBackpack(item4, 3);

        addItemIfClickedToInventory(item1, 0);
        addItemIfClickedToInventory(item2, 1);
        addItemIfClickedToInventory(item3,2);
        addItemIfClickedToInventory(item4, 3);
        
        creditsAmount.setText("You have been rewarded with " + Helper.integerToString(FightController.credits) + " credits!");

        amountOfItemsChosen = 0;
    }

    public void save(){
        Helper.closeScene(save);
        FightController.credits = 0;

        try {
            Helper.openScene("game.fxml", "game");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void yourInventory(){

    }


    private Image setAnchorDisplayItem(int index) {
        Anchors[] anchors = Anchors.values();
        if (index >= 0 && index < anchors.length) {
            Item anchor = gameManager.getMonster().getCarriedItems().get(anchors[index]);
            if (anchor != null) {
                return anchor.getItemImage();
            }
        }
        // Return a default image or handle the null case as per your requirement
        return null;
    }

    private Image setInventoryDisplayItem(int index) {
        Image image= new Image("items/empty.png");
        if (gameManager.getMonster().hasBackpack()) {
            Backpack backpack = gameManager.getMonster().backpackExist();
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
        if(gameManager.getMonster().hasBackpack()) {
            imageview.setOnMouseClicked(event -> {
                Image image = imageview.getImage();
                boolean isBackpack = gameManager.getMonster().getCarriedItems().get(Anchors.values()[index]).checkImage(image);

                if (isBackpack) {
                    if(!isShowingInventory){
                        showInventory();
                        isShowingInventory = true;
                    }else {
                        hideInventory();
                        isShowingInventory = false;
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

    private void addItemIfClickedToInventory(ImageView imageView, int index){
        //TODO: Make check if item slots are full.
        imageView.setOnMouseClicked(event -> {
            Item monsterItem = gameManager.getMonster().getCarriedItems().get(Anchors.values()[index]);

            if(amountOfItemsChosen < 1){
                try {
                    gameManager.getMonster().dropItem(monsterItem);
                    gameManager.getEntity().pickupItem(monsterItem);
                } catch (CannotAddEntityException e) {
                    throw new RuntimeException(e);
                }

                imageView.setImage(new Image("items/empty.png"));
                amountOfItemsChosen++;
            }
        });
    }
    private void hoverItem1() {
    	hoverFunction(item1,0);
    }
    
    private void hoverItem2() {
    	hoverFunction(item2,1);
    }
    
    private void hoverItem3() {
    	hoverFunction(item3,2);
    }
    
    private void hoverItem4() {
    	hoverFunction(item4,3);
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

    private void showPotionStats(Item item) {
        if (item instanceof Potion) {
            showItemStats(item);
            double health = ((Potion) item).getHealthAmount();
            special.setText(Helper.integerToString(health));
        }
    }
    private void hoverFunction(ImageView imageview, int index) {
        imageview.setOnMouseEntered(event -> {
        	Item monsterItem = gameManager.getMonster().getCarriedItems().get(Anchors.values()[index]);

            if (monsterItem != null) {
                showWeaponStats(monsterItem);
                showAmorStats(monsterItem);
                showBackpackStats(monsterItem);
                showPotionStats(monsterItem);
            }
        });
        
        imageview.setOnMouseExited(event -> {
        	emptyStats();
        });
	}
}
