package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private int playerCoins;
    private List<Tower> reservedTowerList = new ArrayList<Tower>(5);
    private List<UpgradeItems> listUpgradeCardsInInventory = new ArrayList<UpgradeItems>(5);
    private List<Tower> currentTowerList = new ArrayList<>(5);
    private final List<Tower> defaultTowers = new ArrayList<>();

    public InventoryService(){
        this.playerCoins = 150;
        defaultTowers.addAll(List.of(
                new Tower("Fire",40,20,3000),
                new Tower("Water",40,20,3000),
                new Tower("Gold",40,20,3000),
                new Tower("Ruby",40,20,3000),
                new Tower("Coal",40,20,3000))
        );
    }

    public int getPlayerCoins(){return playerCoins;}
    public void setPlayerCoins(int playerCoins){this.playerCoins = playerCoins;}

    public void sellTower(Tower selectedCurrentTowers){
        this.setPlayerCoins(this.playerCoins + selectedCurrentTowers.getCost());
        this.currentTowerList.remove(selectedCurrentTowers);
    }
    public void putTowerBackToReserved(Tower selectedCurrentTowers) throws Exception{
        if(this.reservedTowerList.size() < 5) {
            this.reservedTowerList.add(selectedCurrentTowers);
            this.currentTowerList.remove(selectedCurrentTowers);
        }else
            throw new Exception("Can't add more towers back to reserved");
    }
    public void addTowerToCurrent(Tower selectedReservedTowers) throws Exception{
        if(this.currentTowerList.size() < 5) {
            this.currentTowerList.add(selectedReservedTowers);
            this.reservedTowerList.remove(selectedReservedTowers);
        }else
            throw new Exception("Can't add more towers into current");
    }
    public void upgradeTower (UpgradeItems selectedUpgradeCard, Tower selectedCurrentTowers) throws Exception {
        try {
            if (selectedUpgradeCard.getName().contains("Changing Type")) {
                selectedCurrentTowers.changeTypeResource(selectedUpgradeCard.getNewTypeTower());
                System.out.println("Card type:" + selectedUpgradeCard.getNewTypeTower());
                System.out.println("New type: " + selectedCurrentTowers.getName());
            } else {
                selectedCurrentTowers.levelIncrement();
                selectedCurrentTowers.upgradeTime(selectedUpgradeCard.getImprovedTime());
                selectedCurrentTowers.upgradeResourceAmount(selectedUpgradeCard.getImprovedAmountResource());
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
        System.out.println("selectedCurrentTowers in inventory: " + selectedCurrentTowers.getName());
    }
    /**
     * @return List of the default towers which can be selected by player on the setup page
     */
    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    /** Get list of the current towers are used in round
     * @return currentTowerList
     */
    public List<Tower> getCurrentTowerList() {
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
