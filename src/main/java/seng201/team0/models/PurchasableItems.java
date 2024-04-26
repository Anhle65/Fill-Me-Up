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
}
