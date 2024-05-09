package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Purchasable class to keep track all the items can be bought from shop
 */
public class PurchasableItems {
    private String nameItem;
    private float improvedTime;
    private int improvedAmountResource;
    private int cost;
    private String newType;
    private List<PurchasableItems> items = new ArrayList<>();

    /**
     * This constructor to initialize tower as an item in shop
     * @param name
     * @param tower
     */
    public PurchasableItems(String name, Tower tower){
        this.nameItem = name;
        this.improvedAmountResource = 0;
        this.improvedTime = 0;
        this.cost = tower.getCost();
    }

    /**
     * This constructor to initialize an upgrade card which can be used for tower
     * @param name
     * @param improvedAmountResource
     * @param improvedTime
     * @param cost
     */
    public PurchasableItems(String name, int improvedAmountResource, float improvedTime, int cost) {
        this.nameItem = name;
        this.improvedAmountResource = improvedAmountResource;
        this.improvedTime = improvedTime;
        this.cost = cost;
    }

    /**
     * This constructor to initialize the changing card which can be used to change the type of tower
     * @param name
     * @param newType
     * @param cost
     */
    public PurchasableItems(String name, String newType, int cost){
        this.nameItem = name;
        this.improvedAmountResource = 0;
        this.improvedTime = 0;
        this.newType = newType;
        this.cost = cost;
    }

    /**
     * @return name of the Item
     */
    public String getNameItem(){
        return nameItem;
    };

    /**
     * @return the price to buy the item in shop
     */
    public int getCostItem(){
        return cost;
    }

    /**
     * @return the amount resource which can be added to upgrade the tower
     */
    public int getImprovedAmountResource(){
        return improvedAmountResource;
    }

    /**
     * @return the amount of time which can decrement the reload time of Tower
     */
    public float getImprovedTime(){ return improvedTime;}
}
