package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItem;
import seng201.team0.services.ShopService;
import seng201.team0.models.UpgradeItems;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.*;


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
    private ShopService shopService;
    private int selectedItemIndex = -1;
    private List<Tower> listTowersInShop = new ArrayList<>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Coal", "Diamond"};
    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
    private final Integer[] COST = {10, 15, 25, 40, 50};
    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
    public ShopController(EnvironmentManager environmentManager, ShopService shopService) {
        this.environmentManager = environmentManager;
        this.shopService = shopService;
    }

    /**
     * Initialize 3 random upgrade cards and 3 random towers which player can buy from shop to upgrade tower's statistics
     * or have new tower. The price is different depending on how good the upgrade card can do to the tower.
     */
    public void initialize(){
//        playerCoins.setText(String.valueOf(environmentManager.getPlayerCoins()));
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
        listTowersInShop.addAll(List.of(
                new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], 40, 20, 6),
                new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], 40, 20, 6),
                new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(2)], 40, 20, 6)
        ));
        shopService.setListTowersInShop(listTowersInShop);

        // Create 3 random upgrade card
        listUpgradeCardsInShop.addAll(List.of(
                new UpgradeItems("Upgrade Time", 0, TIME_ENHANCEMENT[timeIndex], COST[timeIndex]),
                new UpgradeItems("Upgrade\nAmount", RESOURCE_ENHANCEMENT[enhancedResourceIndex], 0, COST[enhancedResourceIndex]),
                new UpgradeItems("Changing Type\nTower to " + TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)], TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)] , 60)
        ));
        shopService.setListUpgradeCardsInShop(listUpgradeCardsInShop);
        System.out.println("This is the first upgrade item in Shop: " + shopService.getListUpgradeCardsInShop().get(0).getName());

        ArrayList<PurchasableItem> allItemsInShop = new ArrayList<>();
        for(UpgradeItems card : listUpgradeCardsInShop){
            allItemsInShop.add(card);
        }
        for(Tower tower : listTowersInShop){
            allItemsInShop.add(tower);
        }

        System.out.println("Length of items in Shop: " + allItemsInShop.size());
        // Set the upgrade card in shop
//        for (int i = 0; i < listUpgradeCardButtons.size(); i++) {
//            int finalI = i; // variables used within lambdas must be final
//            UpgradeItems item = listUpgradeCardsInShop.get(finalI);
//            listUpgradeCardButtons.get(finalI).setText(item.getNameItem());
//        }
//
//        // Set the tower in shop
//        for (int i = 0; i < listTowersInShopButtons.size(); i++) {
//            int finalI = i; // variables used within lambdas must be final
//            Tower item = listTowersInShop.get(finalI);
//            listTowersInShopButtons.get(finalI).setText("Tower:\n" + item.getType());
//        }

        // Change color of the button which is chosen by player
        for (int i = 0; i < listItemsButton.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            PurchasableItem item = allItemsInShop.get(finalI);
            listItemsButton.get(finalI).setText(item.getName());
            listItemsButton.get(i).setOnAction(event -> {
                selectedItemIndex = finalI;
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
