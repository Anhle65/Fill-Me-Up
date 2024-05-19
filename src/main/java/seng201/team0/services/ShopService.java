package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
//    private final Consumer<Shop> shopScreenLauncher;
//    private final Runnable clearScreen;
    private EnvironmentManager environmentManager;
    private List<Tower> listTowersInShop = new ArrayList<>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<>();
    private List<String> towerType = List.of("Water", "Fire", "Gold", "Coal", "Diamond");
    private int currentCoin = 150;
    public ShopService(EnvironmentManager environmentManager){
        this.environmentManager = environmentManager;
//        this.currentCoin = environmentManager.getPlayerCoins();
    }

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
