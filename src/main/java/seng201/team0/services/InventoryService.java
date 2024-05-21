package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private int playerCoins;
    private final List<Tower> reservedTowerList = new ArrayList<Tower>(5);
    private final List<UpgradeItems> listUpgradeCardsInInventory = new ArrayList<UpgradeItems>(5);
    private List<Tower> currentTowerList = new ArrayList<>();
    private final List<Tower> defaultTowers = new ArrayList<>();

    public InventoryService(){
        defaultTowers.addAll(List.of(
                new Tower("Fire",40,20,3000),
                new Tower("Water",40,20,3000),
                new Tower("Gold",40,20,3000),
                new Tower("Ruby",40,20,3000),
                new Tower("Coal",40,20,3000))
        );
    }
    public void swapTower(){

    }
    public void upgradeTower(UpgradeItems selectedUpgradeCard, Tower selectedCurrentTowers){
        if(selectedUpgradeCard.getName().contains("Changing Type")){
            selectedCurrentTowers.changeTypeResource(selectedUpgradeCard.getNewTypeTower());
            System.out.println("Card type:" + selectedUpgradeCard.getNewTypeTower());
            System.out.println("New type: " + selectedCurrentTowers.getName());
        }else {
            selectedCurrentTowers.levelIncrement();
            selectedCurrentTowers.upgradeTime(selectedUpgradeCard.getImprovedTime());
            selectedCurrentTowers.upgradeResourceAmount(selectedUpgradeCard.getImprovedAmountResource());
//                System.out.println("new stat: " + selectedCurrentTowers.getLevel() + " " + selectedCurrentTowers.getRecoveryTime() +"ms ");
        }
        System.out.println("selectedCurrentTowers in shop: " + selectedCurrentTowers);
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
        return currentTowerList;
    }

    /**
     * set the current towers are used
     * @param currentTowerList ArrayList<Tower>
     */
    public void setCurrentTowerList(List<Tower> currentTowerList) {
        this.currentTowerList = currentTowerList;
    }

    /**
     * Get list of the upgrade card in Inventory
     * @return listUpgradeCardsInInventory
     */
    public List<UpgradeItems> getListUpgradeCardsInInventory(){return this.listUpgradeCardsInInventory;}

    /**
     * Get list of the reserved tower in Inventory
     * @return reservedTowerList
     */
    public List<Tower> getReservedTowerList(){return this.reservedTowerList;}
}
