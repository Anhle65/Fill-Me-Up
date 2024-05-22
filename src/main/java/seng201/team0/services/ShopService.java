package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private InventoryService inventoryService;
    private List<Tower> listTowersInShop = new ArrayList<Tower>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<UpgradeItems>();
    private List<String> towerType = List.of("Water", "Fire", "Gold", "Coal", "Ruby");
    private int currentCoin;

    /**
     * A constructor that has the Inventory Instance is parameter and initialize playerCoin
     * @param inventoryService
     */
    public ShopService(InventoryService inventoryService){
        this.inventoryService= inventoryService;
        this.currentCoin = inventoryService.getPlayerCoins();
    }

    public void buy(UpgradeItems item){
        if (this.currentCoin >= item.getCost()) {
            this.currentCoin -= item.getCost();
            inventoryService.setPlayerCoins(this.currentCoin);
            inventoryService.getListUpgradeItemsInInventory().add(item);
        }
    }

    public void buy(Tower tower){
        if(this.currentCoin >= tower.getCost()) {
            this.currentCoin -= tower.getCost();
            inventoryService.setPlayerCoins(this.currentCoin);
            inventoryService.getReservedTowerList().add(tower);
        }
    }

    public void setListTowersInShop(List<Tower> listTowersInShop){
        this.listTowersInShop = listTowersInShop;
    }

    public void setListUpgradeCardsInShop(List<UpgradeItems> listUpgradeCardsInShop){
        this.listUpgradeCardsInShop = listUpgradeCardsInShop;
    }

    public List<UpgradeItems> getListUpgradeCardsInShop(){return this.listUpgradeCardsInShop;}

    public List<Tower> getListTowersInShop(){return this.listTowersInShop;}
}
