package seng201.team0.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class Shop {
    private List<PurchasableItems> defaultItems = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Food", "Coal", "Diamond"};
    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
    private final Integer[] COST = {10, 15, 25, 40, 50};
    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
    public Shop(){
        Random randomResource = new Random();
        Random randomTimeUpgrade = new Random();
        Random randomResourceUpgrade = new Random();
        int resourceIndex=  randomResource.nextInt(TYPE_RESOURCES.length);
        int timeIndex = randomTimeUpgrade.nextInt(TIME_ENHANCEMENT.length);
        int costIndex = randomResourceUpgrade.nextInt(COST.length);
        defaultItems.addAll(List.of(new PurchasableItems("Upgrade time", 0, TIME_ENHANCEMENT[timeIndex], COST[timeIndex]),
                new PurchasableItems("Upgrade amount resource", RESOURCE_ENHANCEMENT[resourceIndex], 0, COST[resourceIndex]),
//                new PurchasableItems("Upgrade time and amount resource", 3, 1, 17),
                new PurchasableItems("Change the type of tower" + TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)], TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)] , 60),
                new PurchasableItems(TYPE_RESOURCES[resourceIndex], new Tower(TYPE_RESOURCES[resourceIndex], 1, 40, 20, 6, true, false )),
                new PurchasableItems(TYPE_RESOURCES[resourceIndex], new Tower(TYPE_RESOURCES[resourceIndex], 1, 40, 20, 6, true, false )),
                new PurchasableItems(TYPE_RESOURCES[resourceIndex], new Tower(TYPE_RESOURCES[resourceIndex], 1, 40, 20, 6, true, false ))
                ));
    }
    public void changeTypeTower(Tower tower, String newType){
        tower.changeTypeResource(newType);
    }
    public List<PurchasableItems> getAllItems(){return defaultItems;}
}
