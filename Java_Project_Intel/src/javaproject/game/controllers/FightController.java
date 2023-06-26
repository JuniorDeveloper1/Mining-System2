package javaproject.game.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javaproject.exceptions.CannotAddEntityException;
import javaproject.game.Enity.Anchors;
import javaproject.game.Enity.Hero;
import javaproject.game.Enity.Monster;
import javaproject.game.GameManager;
import javaproject.game.Helper.Helper;
import javaproject.game.Items.*;

import java.io.IOException;
import java.util.List;


public class FightController {
    public Hero hero = (Hero) GameManager.getInstance().getEntity();
    public GameManager gameManager = GameManager.getInstance();
    public ImageView heroImage;
    public ImageView monsterImage;

    public Image monsterAttacking = new Image("characters/william-robinson-boss-firing.gif");
    public Image heroAttacking = new Image("characters/wizard_attack.gif");

    public Image monsterIdle = new Image("characters/william-robinson-boss-passive.gif");
    public Image heroIdle = new Image("characters/wizard_idle.gif");


    public Image heroDead = new Image("characters/wizard_dead.gif");
    public Image monsterDead = new Image("characters/william-robinson-boss-dying.gif");

    public Monster monster;
    private Timeline timeline;


    public ProgressBar progressHero;

    public ProgressBar progressMonster;

    public Text heroHP;
    public Text monsterHP;

    public Text heroName;
    public Text monsterName;

    public Button runButton;

    public Text chanceOutput;
    public Text costOutput;

    public Text trapped;

    public Button next;

    public Button hit;
  

    private int monsterHPForCalculate;



    public AnchorPane fightAnchor;
    public AnchorPane inventoryAnchor;
    public ImageView inventoryImage;



    public void initialize() throws CannotAddEntityException {
        this.createMonster();
        this.fightController();
        inventoryController();
        int randomNumber = Helper.generateRandomPercentage(30,15);
        costOutput.setText(String.valueOf(randomNumber));
    }

    private void fightController(){

        next.setVisible(false);
        
        heroName.setText(hero.getName());
        monsterName.setText(monster.getName());
        heroHP.setText(Helper.integerToString(hero.getCurrentHitPoints()));
        monsterHP.setText(Helper.integerToString(monster.getCurrentHitPoints()));
        monsterHPForCalculate = monster.getCurrentHitPoints();
        chanceOutput.setText(Helper.integerToString(gameManager.getRunChance()) + "% chance");
        System.out.println(this.gameManager.getRunChance());
        fightAnchor.setVisible(true);
        inventoryAnchor.setVisible(false);
        this.onInventoryClick();
        this.fightAnimation();
    }
    public void hit() throws CannotAddEntityException {
        timeline.playFromStart();
    }

    public void run() throws IOException {
        int percentage = (int) (Math.random()*100)+1;
        int cost = Integer.parseInt(costOutput.getText());

        if(gameManager.getPlayerCredits().getCredits() - cost > 0){
            gameManager.getPlayerCredits().withdrawCredits(cost);
                if(percentage > this.gameManager.getRunChance()) {
                    System.out.println("You payed " + cost);
                        directToGameController();
                }

        }else {
            runButton.setVisible(false);
            chanceOutput.setVisible(false);
            costOutput.setVisible(false);
            trapped.setVisible(true);
        }
    }

    static int credits = 0;
    public void next() throws IOException {
        if(hero.isAlive()) {
            /**
             * Hero will be rewarded with weapons from the monster.
             *
             * Coins will be given randomly based on the creature HP.
             *
             * User should recieve between Max - 20 coins
             */

        	FightController.credits = Helper.generateRandomPercentage(monsterHPForCalculate, 20);
            gameManager.getPlayerCredits().addCredits(FightController.credits);
            openWinController();
            gameManager.setRunChance(50);
        }else {
            /**
             * Make a dead scene
             */
            System.out.println("Start over, you have died!");
            Helper.closeScene(next);
            Helper.openScene("main_menu.fxml","main menu");
            FightController.credits = 0;
        }
    }
    private void fightAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                	if(hero.isAlive()) {
	                   	 heroHP.setText(Helper.integerToString(hero.getCurrentHitPoints()));
	                     progressHero.setProgress(hero.calculateEntityHP());
                	}
                	

                	
                    heroImage.setImage(heroIdle);
                    hit.setVisible(false);
                    inventoryImage.setVisible(false);
                }),
                new KeyFrame(Duration.seconds(1), event -> {

                    try {

                        if(!hero.isAlive()){
                            heroImage.setImage(heroDead);
                            next.setVisible(true);
                            runButton.setVisible(false);
                            hit.setVisible(false);
                        }else {
                            monsterImage.setImage(monsterIdle);
                        }

                        if(monster.isAlive()) {
                            hero.hit(monster);
                            heroImage.setImage(heroAttacking);

                            monsterHP.setText(Helper.integerToString(monster.getCurrentHitPoints()));
                            progressMonster.setProgress(monster.calculateEntityHP());
                        }else {
                            monsterHP.setText(Helper.integerToString(monster.getCurrentHitPoints()));
                            progressMonster.setProgress(monster.calculateEntityHP());

                            monsterImage.setImage(monsterDead);
                            /**
                             * Total killed:
                             */
                            gameManager.killedEntity();
                            System.out.println(gameManager.getTotalKilled());

                            heroImage.setImage(heroIdle);

                            trapped.setVisible(false);
                            next.setVisible(true);
                            timeline.stop();
                        }

                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Erna: " + monster.getCurrentHitPoints());
                }),
                /**
                 * Key frames for the monster
                 */
                new KeyFrame(Duration.seconds(3), event -> {
                    try {
                        if(hero.isAlive()){
                            monster.hit(hero);
                            heroImage.setImage(heroIdle);
                            monsterImage.setImage(monsterAttacking);

                            heroHP.setText(Helper.integerToString(hero.getCurrentHitPoints()));
                            progressHero.setProgress(hero.calculateEntityHP());
                        }else {
                            heroImage.setImage(heroDead);
                            monsterImage.setImage(monsterIdle);
                            hero.setCurrentHitPoints(0);
                            heroHP.setText(Helper.integerToString(hero.getCurrentHitPoints()));
                            progressHero.setProgress(hero.calculateEntityHP());
                            next.setVisible(true);
                            timeline.stop();
                        }

                    } catch (CannotAddEntityException e) {
                        throw new RuntimeException(e);
                    }

                }),
                new KeyFrame(Duration.seconds(6), event -> {
                    if(!(monster.isAlive())){
                        next.setVisible(true);
                        runButton.setVisible(false);
                        hit.setVisible(false);
                        monsterImage.setImage(monsterDead);
                    }else {
                        hit.setVisible(true);
                        inventoryImage.setVisible(true);
                        monsterImage.setImage(monsterIdle);
                    }

                    if(!hero.isAlive()){
                        heroImage.setImage(heroDead);
                        gameManager.getEntity().setCurrentHitPoints(0);
                        heroHP.setText(Helper.integerToString(0));
                        progressHero.setProgress(0);

                        next.setVisible(true);
                        runButton.setVisible(false);
                        hit.setVisible(false);
                    }else {
                        heroImage.setImage(heroIdle);
                    }



                })
        );

        timeline.setCycleCount(1);
    }
    private void createMonster() throws CannotAddEntityException {
        int currentTurn =gameManager.getTurn().getCurrentTurn();
        int randomHitpoints = 10;
        int randomStrength = 2;
        int randomDamage = 3;
        // hitpoints between 200-60

         if(currentTurn <= 5 ) {
             randomHitpoints = Helper.generateRandomPercentage(110,50);
             randomStrength = Helper.generateRandomPercentage(6,2);
             randomDamage = Helper.generateRandomPercentage(10,5);
         }

         if(currentTurn > 5 && currentTurn <= 10) {
             randomHitpoints = Helper.generateRandomPercentage(200,100);
             randomStrength = Helper.generateRandomPercentage(10,5);
             randomDamage = Helper.generateRandomPercentage(40,10);
         }

        if(currentTurn > 10 && currentTurn <= 20) {
            randomHitpoints = Helper.generateRandomPercentage(650,200);
            randomStrength = Helper.generateRandomPercentage(20,10);
            randomDamage = Helper.generateRandomPercentage(80,20);
        }

        if(currentTurn > 20) {
            randomHitpoints = Helper.generateRandomPercentage(1000,400);
            randomStrength = Helper.generateRandomPercentage(100,20);
            randomDamage = Helper.generateRandomPercentage(80,20);
        }
        /**
         * Monster creation
         */
        monster = new Monster("Monster",randomHitpoints,randomStrength,randomDamage);

        gameManager.setMonster(monster);
    }
    
    private void directToGameController() throws IOException {
        Helper.closeScene(runButton);
        Helper.openScene("game.fxml", "Fight");
    }

    private void openWinController() throws IOException {
        Helper.openScene("win.fxml", "YOU WON");
        Helper.closeScene(next);
    }

    private void onInventoryClick(){
        inventoryImage.setOnMouseClicked(event -> {
            if(fightAnchor.isVisible()){
                fightAnchor.setVisible(false);
                inventoryAnchor.setVisible(true);
            }
        });
    }


    /**
     * Inventory Anchor pane
     */

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


    private void inventoryController(){
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
    }

    public void goBack(){
        inventoryAnchor.setVisible(false);
        fightAnchor.setVisible(true);

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
