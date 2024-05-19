package seng201.team0.models;

public class PurchasableItem {
    private String nameItem;
//    public PurchasableItem(Tower tower){
//        this.nameItem = tower.getType();
//    }
//    public PurchasableItem(String name, int improvedAmountResource, float improvedTime, int cost){
//        this.nameItem = name;
//    }
    /**
     * @return name of the Item
     */
    public String getName(){
        return nameItem;
    };
}
