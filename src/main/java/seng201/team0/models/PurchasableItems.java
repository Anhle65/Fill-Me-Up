package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

public class PurchasableItems {
    private String nameItem;
    private float improvedTime;
    private int improvedAmountResource;
    private int cost;
    private String newType;
    private List<PurchasableItems> items = new ArrayList<>();
    public PurchasableItems(String name, Tower tower){
        this.nameItem = name;
        this.improvedAmountResource = 0;
        this.improvedTime = 0;
        this.cost = tower.getCost();
    }
    public PurchasableItems(String name, int improvedAmountResource, float improvedTime, int cost) {
        this.nameItem = name;
        this.improvedAmountResource = improvedAmountResource;
        this.improvedTime = improvedTime;
        this.cost = cost;
    }
    public PurchasableItems(String name, String newType, int cost){
        this.nameItem = name;
        this.improvedAmountResource = 0;
        this.improvedTime = 0;
        this.newType = newType;
        this.cost = cost;
    }

    /**
     *
     * @param item
     * @return name of the Item
     */
    public String getNameItem(PurchasableItems item){
        return item.nameItem;
    };

    /**
     *
     * @param item
     * @return the price to buy the item in shop
     */
    public int getCostItem(PurchasableItems item){
        return item.cost;
    }

    /**
     *
     * @param item
     * @return the amount resource which can be added to upgrade the tower
     */
    public int getImprovedAmountResource(PurchasableItems item){
        return item.improvedAmountResource;
    }

    /**
     *
     * @param item
     * @return the amount of time which can decrement the reload time of Tower
     */
    public float getImprovedTime(PurchasableItems item){
        return item.improvedTime;
    }
}
