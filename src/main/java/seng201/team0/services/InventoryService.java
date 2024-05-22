package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that interact with ShopService, and Controller during the game
 */
public class InventoryService {
    private int playerCoins;
    private List<Tower> reservedTowerList = new ArrayList<Tower>(5);
    private List<UpgradeItems> listUpgradeCardsInInventory = new ArrayList<UpgradeItems>(5);
    private List<Tower> currentTowerList = new ArrayList<>(5);
    private final List<Tower> defaultTowers = new ArrayList<>();
    private TowerService towerService;

    /**
     * Initialize the Inventory and its properties
     */
    public InventoryService(TowerService towerService){
        this.towerService = towerService;
        defaultTowers.addAll(List.of(
                new Tower("Fire",20,20,2000),
                new Tower("Water",30,20,3000),
                new Tower("Gold",35,20,2000),
                new Tower("Ruby",40,25,3000),
                new Tower("Coal",15,15,2000))
        );
    }

    /**
     * Get the playerCoins
     * @return playerCoins
     */
    public int getPlayerCoins(){return playerCoins;}

    /**
     * Set the playerCoins
     * @param playerCoins int
     */
    public void setPlayerCoins(int playerCoins){this.playerCoins = playerCoins;}

    /**
     * Sell the selected tower in current used list
     * @param selectedCurrentTowers Tower
     */
    public void sellTower(Tower selectedCurrentTowers){
        this.setPlayerCoins(this.playerCoins + selectedCurrentTowers.getCost());
        this.currentTowerList.remove(selectedCurrentTowers);
    }

    /**
     * Move the selected tower in current used list to reserved list
     * @param selectedCurrentTowers Tower
     * @throws Exception when the size reserved list exceed 5
     */
    public void putTowerBackToReserved(Tower selectedCurrentTowers) throws Exception{
        if(this.reservedTowerList.size() < 5) {
            this.reservedTowerList.add(selectedCurrentTowers);
            this.currentTowerList.remove(selectedCurrentTowers);
        }else
            throw new Exception("Can't add more towers back to reserved");
    }

    /**
     * Move the selected tower in reserved list to current used list
     * @param selectedReservedTowers Tower
     * @throws Exception
     */
    public void addTowerToCurrent(Tower selectedReservedTowers) throws Exception{
        if(this.currentTowerList.size() < 5) {
            this.currentTowerList.add(selectedReservedTowers);
            this.reservedTowerList.remove(selectedReservedTowers);
        }else
            throw new Exception("Can't add more towers into current");
    }

    /**
     * Upgrade the selected Tower's statistics and consume the selected upgrade item
     * @param selectedUpgradeCard UpgradeItems
     * @param selectedCurrentTowers Tower
     * @throws Exception
     */
    public void upgradeTower(UpgradeItems selectedUpgradeCard, Tower selectedCurrentTowers) throws Exception {
        try {
            if (selectedUpgradeCard.getName().contains("Changing Type")) {
                towerService.changeTypeResource(selectedCurrentTowers, selectedUpgradeCard.getNewTypeTower());
                System.out.println("Card type:" + selectedUpgradeCard.getNewTypeTower());
                System.out.println("New type: " + selectedCurrentTowers.getName());
            } else {
                towerService.levelIncrement(selectedCurrentTowers);
                towerService.upgradeTime(selectedCurrentTowers, selectedUpgradeCard.getImprovedTime());
                towerService.upgradeResourceAmount(selectedCurrentTowers, selectedUpgradeCard.getImprovedAmountResource());
            }
            this.getListUpgradeItemsInInventory().remove(selectedUpgradeCard);
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Get the default towers which can be selected by player on the setup page
     * @return defaultTowers
     */
    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    /**
     * Get list of the current towers are used in round
     * @return currentTowerList
     */
    public List<Tower> getCurrentUsedTowerList() {
        return this.currentTowerList;
    }

    /**
     * set the current towers are used
     * @param currentTowerList ArrayList<Tower>
     */
    public void setCurrentTowerList(List<Tower> currentTowerList) {
        this.currentTowerList = new ArrayList<Tower>(currentTowerList);
    }

    /**
     * Get list of the upgrade card in Inventory
     * @return listUpgradeCardsInInventory
     */
    public List<UpgradeItems> getListUpgradeItemsInInventory(){return this.listUpgradeCardsInInventory;}

    /**
     * Get list of the upgrade card in Inventory
     * @param listUpgradeCardsInInventory ArrayList<UpgradeItems>
     */
    public void setListUpgradeItemsInInventory(List<UpgradeItems> listUpgradeCardsInInventory){
        this.listUpgradeCardsInInventory = new ArrayList<>(listUpgradeCardsInInventory);
    }

    /**
     * Get list of the reserved tower in Inventory
     * @return reservedTowerList
     */
    public List<Tower> getReservedTowerList(){return this.reservedTowerList;}

    /**
     * set the current towers are used
     * @param reservedTowerList ArrayList<Tower>
     */
    public void setReservedTowerList(List<Tower> reservedTowerList) {
        this.reservedTowerList = new ArrayList<>(reservedTowerList);
    }
}
