package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private InventoryService inventoryService;
    private List<Tower> listTowersInShop = new ArrayList<Tower>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<UpgradeItems>();
    private List<String> towerType = List.of("Water", "Fire", "Gold", "Coal", "Ruby");
    private int currentCoin = 150;
    public ShopService(InventoryService inventoryService){
        this.inventoryService= inventoryService;
//        this.currentCoin = environmentManager.getPlayerCoins();
    }

    public void buy(UpgradeItems item){
        if(this.currentCoin >= item.getCost()){
            this.currentCoin -= item.getCost();
            inventoryService.getListUpgradeCardsInInventory().add(item);
        }
    }

    public void buy(Tower item){
        if(this.currentCoin >= item.getCost()){
            this.currentCoin -= item.getCost();
            inventoryService.getReservedTowerList().add(item);
        }
    }

    public int getCurrentCoin(){return this.currentCoin;}

    public void setListTowersInShop(List<Tower> listTowersInShop){
        this.listTowersInShop = listTowersInShop;
    }

    public void setListUpgradeCardsInShop(List<UpgradeItems> listUpgradeCardsInShop){
        this.listUpgradeCardsInShop = listUpgradeCardsInShop;
    }

    public List<UpgradeItems> getListUpgradeCardsInShop(){return this.listUpgradeCardsInShop;}

    public List<Tower> getListTowersInShop(){return this.listTowersInShop;}
}
