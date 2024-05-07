package seng201.team0.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class Shop {
    private List<PurchasableItems> defaultItems = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Water", "Fire", "Fire", "Gold", "Food", "Food", "Coal", "Coal", "Diamond"};
    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
    private final Integer[] COST = {10, 15, 25, 40, 50};
    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
    public Shop(){
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
                new PurchasableItems(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], 1, 40, 20, 6)),
                new PurchasableItems(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], 1, 40, 20, 6)),
                new PurchasableItems(TYPE_RESOURCES[towerRandomTypeIndexes.get(2)], new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(2)], 1, 40, 20, 6))
                ));
    }
    public void changeTypeTower(Tower tower, String newType){
        tower.changeTypeResource(newType);
    }
    public List<PurchasableItems> getAllItems(){return defaultItems;}
}
