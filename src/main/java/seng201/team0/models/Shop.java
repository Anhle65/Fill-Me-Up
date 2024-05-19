package seng201.team0.models;

import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

public class Shop {
//    private final Consumer<Shop> shopScreenLauncher;
//    private final Runnable clearScreen;
    private EnvironmentManager environmentManager;
    private List<Tower> listTowersInShop;
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<>();
    private List<String> towerType = List.of("Water", "Fire", "Gold", "Coal", "Diamond");
    private int currentCoin;
    public Shop(){
//        this.currentCoin = environmentManager.getPlayerCoins();
    }
//    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Food", "Coal", "Diamond"};
//    private final float[] TIME_ENHANCEMENT = {1, 1.5f, 2, 2.5f, 3};
//    private final Integer[] COST = {10, 15, 25, 40, 50};
//    private final Integer[] RESOURCE_ENHANCEMENT = {5, 7, 10, 12, 15};
//    public Shop(){
//        Random randomResource = new Random();
//        Random randomTimeUpgrade = new Random();
//        Random randomTypeResource = new Random();
//        int enhancedResourceIndex=  randomResource.nextInt(RESOURCE_ENHANCEMENT.length);
//        int timeIndex = randomTimeUpgrade.nextInt(TIME_ENHANCEMENT.length);
//        List<Integer> towerRandomTypeIndexes = List.of(randomTypeResource.nextInt(TYPE_RESOURCES.length),
//                randomTypeResource.nextInt(TYPE_RESOURCES.length), randomTypeResource.nextInt(TYPE_RESOURCES.length));
//        listTowersInShop.addAll(List.of(
//                new PurchasableItems(new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(0)], 40, 20, 6)),
//                new PurchasableItems(new Tower(TYPE_RESOURCES[towerRandomTypeIndexes.get(1)], 40, 20, 6)),
//        ));
//        listUpgradeCards.addAll(List.of(
//                new PurchasableItems("Upgrade time", 0, TIME_ENHANCEMENT[timeIndex], COST[timeIndex]),
//                new PurchasableItems("Upgrade amount resource", RESOURCE_ENHANCEMENT[enhancedResourceIndex], 0, COST[enhancedResourceIndex]),
//                new PurchasableItems("Change the type of tower" + TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)], TYPE_RESOURCES[randomResource.nextInt(TYPE_RESOURCES.length)] , 60)
//        ));
//    }
//    /**
//     * Get the list of towers in shop
//     * @return listTowerInShop
//     */
//    public List<PurchasableItems> getListTowersCanBuy(){
//        return this.listTowersInShop;
//    }
//
//    /**
//     * Get the list of upgrade items in shop
//     * @return listUpgradeCards
//     */
//    public List<PurchasableItems> getListUpgradeCards(){
//        return this.listUpgradeCards;
//    }
//    /**
//     * Get the player coins
//     * @return playerCoins
//     */
//    public int getPlayerCoins(){
//        return this.playerCoins;
//    }
//    public Shop(Consumer<Shop>shopScreenLauncher, Runnable clearScreen ){
//        this.shopScreenLauncher = shopScreenLauncher;
//        this.clearScreen = clearScreen;
//    }
//    public void launchShopScreen() { shopScreenLauncher.accept(this);}
//    public void setCurrentCoin(){this.currentCoin = environmentManager.getPlayerCoins();}
    public void buy(UpgradeItems item){
        if(this.currentCoin >= item.getCost()){
            this.currentCoin -= item.getCost();
            environmentManager.getListUpgradeCardsInInventory().add(item);
        }
    }

    public void buy(Tower item){
        if(this.currentCoin >= item.getCost()){
            this.currentCoin -= item.getCost();
            environmentManager.getReservedTowerList().add(item);
        }
    }
    public void setListTowersInShop(List<Tower> listTowersInShop){
        this.listTowersInShop = listTowersInShop;
    }

    public void setListUpgradeCardsInShop(List<UpgradeItems> listUpgradeCardsInShop){
        this.listUpgradeCardsInShop = listUpgradeCardsInShop;
    }

    public List<UpgradeItems> getListUpgradeCardsInShop(){return this.listUpgradeCardsInShop;}
}
