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
    public ShopService(InventoryService inventoryService){
        this.inventoryService= inventoryService;
        this.currentCoin = inventoryService.getPlayerCoins();
    }

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

    public void buy(Tower item) throws Exception{
        if (inventoryService.getListUpgradeItemsInInventory().size() < 5) {
            if (this.currentCoin >= item.getCost()) {
                this.currentCoin -= item.getCost();
                inventoryService.setPlayerCoins(this.currentCoin);
                inventoryService.getReservedTowerList().add(item);
            }
        }
        else {
            throw new Exception("Can't have more than 5 tower in reserved");
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
