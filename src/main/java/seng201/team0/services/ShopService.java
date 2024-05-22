package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that interact with InventoryService, ShopController
 */
public class ShopService {
    private InventoryService inventoryService;
    private List<Tower> listTowersInShop = new ArrayList<Tower>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<UpgradeItems>();
    private List<String> towerType = List.of("Water", "Fire", "Gold", "Coal", "Ruby");
    private int currentCoin;

    /**
     * A constructor that has the Inventory Instance is parameter and initialize playerCoin
     * @param inventoryService InventoryService
     */
    public ShopService(InventoryService inventoryService){
        this.inventoryService= inventoryService;
        this.currentCoin = inventoryService.getPlayerCoins();
    }

    /**
     * Update the playerCoin and listUpgradeItemsInInventory if remaining coins is enough and
     * the size listUpgradeItemsInInventory is less than 5, otherwise won't update anything if not enough coins or throw an Exception.
     * @param item UpgradeItems
     * @throws Exception
     */
    public void buy(UpgradeItems item) throws Exception{
        if (inventoryService.getListUpgradeItemsInInventory().size() < 5) {
            if (this.currentCoin >= item.getCost()) {
                this.currentCoin -= item.getCost();
                inventoryService.setPlayerCoins(this.currentCoin);
                inventoryService.getListUpgradeItemsInInventory().add(item);
            }
        }else {
            throw new Exception("Can't have more than 5 items");
        }
    }

    /**
     * Update the playerCoin and reservedTowerList if remaining coins is enough and
     * the size reservedTowerList is less than 5, otherwise won't update anything if not enough coins or throw an Exception.
     * @param tower Tower
     * @throws Exception
     */
    public void buy(Tower tower) throws Exception{
        if (inventoryService.getReservedTowerList().size() < 5) {
            if (this.currentCoin >= tower.getCost()) {
                this.currentCoin -= tower.getCost();
                inventoryService.setPlayerCoins(this.currentCoin);
                inventoryService.getReservedTowerList().add(tower);
            }
        }
        else {
            throw new Exception("Can't have more than 5 tower in reserved");
        }
    }

    /**
     * Set the listTowerInShop to be the input listTowersInShop
     * @param listTowersInShop List<Tower>
     */
    public void setListTowersInShop(List<Tower> listTowersInShop){
        this.listTowersInShop = listTowersInShop;
    }

    /**
     * Set the listUpgradeItemsInShop to be the input listUpgradeCardsInShop
     * @param listUpgradeCardsInShop List<UpgradeItems>
     */
    public void setListUpgradeItemsInShop(List<UpgradeItems> listUpgradeCardsInShop){
        this.listUpgradeCardsInShop = listUpgradeCardsInShop;
    }

    /**
     * Get the listUpgradeItemsInShop
     * @return listUpgradeCardsInShop
     */
    public List<UpgradeItems> getListUpgradeItemsInShop(){return this.listUpgradeCardsInShop;}

    /**
     * Get the listTowersInShop
     * @return listTowersInShop
     */
    public List<Tower> getListTowersInShop(){return this.listTowersInShop;}
}
