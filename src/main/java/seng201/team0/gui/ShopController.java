package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItems;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class ShopController {
    @FXML
    private Label playerCoins;
    @FXML
    private Button upgradeTime;
    @FXML
    private Button upgradeAmountResource;
    @FXML
    private Button changeTypeResource;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    private int selectedItemIndex = -1;
    private List<PurchasableItems> defaultItems = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Food", "Coal", "Diamond"};
    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
    private final Integer[] COST = {10, 15, 25, 40, 50};
    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
    public void initialize(){
        List<Button> itemButtons = List.of(upgradeTime, upgradeAmountResource, changeTypeResource, tower1, tower2, tower3);
        Random randomResource = new Random();
        Random randomTimeUpgrade = new Random();
        Random randomTypeResource = new Random();
        int enhancedResourceIndex=  randomResource.nextInt(RESOURCE_ENHANCEMENT.length);
        int timeIndex = randomTimeUpgrade.nextInt(TIME_ENHANCEMENT.length);
        List<Integer> towerRandomTypeIndexes = List.of(randomTypeResource.nextInt(TYPE_RESOURCES.length),
                randomTypeResource.nextInt(TYPE_RESOURCES.length), randomTypeResource.nextInt(TYPE_RESOURCES.length));
        defaultItems.addAll(List.of(new PurchasableItems("Upgrade time", 0, TIME_ENHANCEMENT[timeIndex], COST[timeIndex]),
                new PurchasableItems("Upgrade amount resource", RESOURCE_ENHANCEMENT[enhancedResourceIndex], 0, COST[enhancedResourceIndex]),
                new PurchasableItems("Change the type of tower" + TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)], TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)] , 60),
                new PurchasableItems("Tower", new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], 40, 20, 6)),
                new PurchasableItems("Tower", new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], 40, 20, 6)),
                new PurchasableItems("Tower", new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(2)], 40, 20, 6))
        ));
        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            PurchasableItems item = defaultItems.get(finalI);
            if(item.getNameItem().equals("Change the type of tower")){
                itemButtons.get(i).setText("Name: " + item.getNameItem()
                                            +"\nChange Tower to ");
//                updateStats(defaultItems.get(finalI));
            };
            selectedItemIndex = finalI;
            itemButtons.forEach(button -> {
                if (button == itemButtons.get(finalI)) {
                    button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                } else {
                    button.setStyle("");
                }
            });
        };
    }
    //        for (int i = 0; i < selectedTowerButtons.size(); i++) {
//            int finalI = i; // variables used within lambdas must be final
//            selectedTowerButtons.get(i).setOnAction(event -> {
//                if (selectedTowerIndex != -1) {
//                    selectedTowerButtons.get(finalI).setText(environmentManager.getDefaultTowers().get(selectedTowerIndex).getType());
//                    selectedTowers[finalI] = environmentManager.getDefaultTowers().get(selectedTowerIndex);
//                }
//            });
//        }
    public List<PurchasableItems> getAllItems(){return defaultItems;}
    public void updateStats(PurchasableItems items){

    }
}
