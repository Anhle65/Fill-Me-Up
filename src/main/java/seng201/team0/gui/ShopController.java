package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItems;
import seng201.team0.models.Shop;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class ShopController {
    @FXML
    private Label playerCoins;
    @FXML
    private Button upgradeCard1;
    @FXML
    private Button upgradeCard2;
    @FXML
    private Button upgradeCard3;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    private EnvironmentManager environmentManager;
    private int selectedTowerIndex = -1;
    private int selectedCardIndex = -1;
    private List<PurchasableItems> listTowersCanBuy = new ArrayList<>();
    private List<PurchasableItems> listUpgradeCards = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Coal", "Diamond"};
    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
    private final Integer[] COST = {10, 15, 25, 40, 50};
    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
    public ShopController(EnvironmentManager environmentManager) {this.environmentManager = environmentManager;}

    /**
     * Initialize 3 random upgrade cards and 3 random towers which player can buy from shop to upgrade tower's statistics
     * or have new tower. The price is different depending on how good the upgrade card can do to the tower.
     */
    public void initialize(){
        List<Button> listUpgradeCardButtons = List.of(upgradeCard1, upgradeCard2, upgradeCard3);
        List<Button> listTowersInShopButtons = List.of(tower1, tower2, tower3);
        List<Button> listItemsButton = List.of(upgradeCard1, upgradeCard2, upgradeCard3, tower1, tower2, tower3);
        Random randomResource = new Random();
        Random randomTimeUpgrade = new Random();
        Random randomTypeResource = new Random();
        int enhancedResourceIndex=  randomResource.nextInt(RESOURCE_ENHANCEMENT.length);
        int timeIndex = randomTimeUpgrade.nextInt(TIME_ENHANCEMENT.length);
        List<Integer> towerRandomTypeIndexes = List.of(randomTypeResource.nextInt(TYPE_RESOURCES.length),
                randomTypeResource.nextInt(TYPE_RESOURCES.length), randomTypeResource.nextInt(TYPE_RESOURCES.length));

        // Create 3 random tower
        listTowersCanBuy.addAll(List.of(
                new PurchasableItems(new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], 40, 20, 6)),
                new PurchasableItems(new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], 40, 20, 6)),
                new PurchasableItems(new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(2)], 40, 20, 6))
        ));

        // Create 3 random upgrade card
        listUpgradeCards.addAll(List.of(
                new PurchasableItems("Upgrade Time", 0, TIME_ENHANCEMENT[timeIndex], COST[timeIndex]),
                new PurchasableItems("Upgrade\nAmount", RESOURCE_ENHANCEMENT[enhancedResourceIndex], 0, COST[enhancedResourceIndex]),
                new PurchasableItems("Changing Type\nTower to " + TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)], TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)] , 60)
        ));

        // Set the upgrade card in shop
        for (int i = 0; i < listUpgradeCardButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            PurchasableItems item = listUpgradeCards.get(finalI);
            listUpgradeCardButtons.get(finalI).setText(item.getNameItem());
        }

        // Set the tower in shop
        for (int i = 0; i < listTowersInShopButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            PurchasableItems item = listTowersCanBuy.get(finalI);
            listTowersInShopButtons.get(finalI).setText("Tower: " + item.getNameItem());
        }

        // Change color of the button which is chosen by player
        for (int i = 0; i < listItemsButton.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            listItemsButton.get(i).setOnAction(event -> {
//                updateStats(environmentManager.getDefaultTowers().get(finalI));
                selectedTowerIndex = finalI;
                listItemsButton.forEach(button -> {
                    if (button == listItemsButton.get(finalI)) {
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 10px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }
    public void onBuyClicked(){
        System.out.println("Clicked on Buy button");
    }

    public void onNextClicked(){
        System.out.println("Clicked on Next button");
    }
}
