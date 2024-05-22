package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.InventoryService;
import seng201.team0.services.TowerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class InventoryServiceTest {
    private InventoryService inventoryService;
    private TowerService towerService;
    private Tower mockedReservedTower;
    private Tower mockedCurrentUsedTower;
    private Tower mockedCurrentUsedTower2;
    private UpgradeItems mockedChangeTypeItem;
    private UpgradeItems mockedUpgradeTimeItem;
    private UpgradeItems mockedUpgradeResourceItem;

    /**
     * Set up before each test set playerCoin, create mockedReservedTower, mockedCurrentUsedTower, Inventory instance
     */
    @BeforeEach
    public void setUpTest(){
        towerService = new TowerService();
        inventoryService = new InventoryService(towerService);
        mockedReservedTower = new Tower("Water", 30, 20, 3000);
        mockedCurrentUsedTower = new Tower("Fire", 20, 20, 3000);
        mockedCurrentUsedTower2 = new Tower("Fire", 20, 20, 1000);
        inventoryService.setPlayerCoins(30);
        mockedChangeTypeItem = new UpgradeItems("Changing Type", "Gold", 20);
        mockedUpgradeTimeItem = new UpgradeItems("Upgrade Time", 0, 2000, 20);
        mockedUpgradeResourceItem = new UpgradeItems("Upgrade Resource", 10, 0, 20);
        List<UpgradeItems> listUpgradeItems = List.of(mockedUpgradeResourceItem, mockedUpgradeTimeItem, mockedChangeTypeItem);
        inventoryService.setListUpgradeItemsInInventory(listUpgradeItems);
        List<Tower> listReservedTowers = List.of(mockedReservedTower);
        List<Tower> listCurrentTowers = List.of(mockedCurrentUsedTower);
        inventoryService.setReservedTowerList(listReservedTowers);
        inventoryService.setCurrentTowerList(listCurrentTowers);
    }

    /**
     * Test sellTower method when called will remove the selected tower from the current used tower list
     * and increase the coin to player coins
     */
    @Test
    void testSellTower(){
        inventoryService.sellTower(mockedCurrentUsedTower);
        int sizeCurrentTowerList = inventoryService.getCurrentUsedTowerList().size();
        assertEquals(50, inventoryService.getPlayerCoins());
        assertEquals(0, sizeCurrentTowerList);
    }

    /**
     * Test moving the selected current Tower to the reserved list when
     * the size of reserved Towers list is smaller than 5
     */
    @Test
    void testPutTowerBackToReservedWhenSizeOfReservedListSmallerThanFive() throws Exception{
        inventoryService.putTowerBackToReserved(mockedCurrentUsedTower);
        assertEquals(0, inventoryService.getCurrentUsedTowerList().size());
        assertEquals(2, inventoryService.getReservedTowerList().size());
    }

    /**
     * Test moving the selected current Tower to the reserved list when
     * the size of reserved towers list is equal or bigger than 5 will throw an Exception
     * @throws Exception
     */
    @Test
    void testPutTowerBackToReservedWhenSizeOfReservedListNotSmallerThanFive() throws Exception{
        List<Tower> reservedTowers = inventoryService.getReservedTowerList();
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        assertEquals(5, inventoryService.getReservedTowerList().size());
        Exception exception = assertThrows(Exception.class, () -> inventoryService.putTowerBackToReserved(mockedCurrentUsedTower));
        assertEquals("Can't add more towers back to reserved", exception.getMessage());
    }

    /**
     * Test moving the selected Tower in current used towers to the reserved list when
     * the size of current used towers list is smaller than 5
     */
    @Test
    void testAddTowerToCurrentWhenSizeOfCurrentListSmallerThanFive() throws Exception{
        inventoryService.addTowerToCurrent(mockedReservedTower);
        assertEquals(0, inventoryService.getReservedTowerList().size());
        assertEquals(2, inventoryService.getCurrentUsedTowerList().size());
    }

    /**
     * Test moving the selected Tower in current used towers to the reserved list when
     * the size of current used towers list is equal or bigger than 5 will throw an Exception
     * @throws Exception
     */
    @Test
    void testAddTowerToCurrentWhenSizeOfCurrentListNotSmallerThanFive() throws Exception{
        List<Tower> currentUsedTowers = inventoryService.getCurrentUsedTowerList();
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        assertEquals(5, inventoryService.getCurrentUsedTowerList().size());
        Exception exception = assertThrows(Exception.class, () -> inventoryService.addTowerToCurrent(mockedReservedTower));
        assertEquals("Can't add more towers into current", exception.getMessage());
    }

    /**
     * Test when the Card is Change Type item which change the selected Tower's type to the type in item
     * and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsChangeTypeTower() throws Exception {
        inventoryService.upgradeTower(mockedChangeTypeItem, mockedCurrentUsedTower);
        String newType = mockedChangeTypeItem.getNewTypeTower();
        assertEquals(newType, mockedCurrentUsedTower.getName()); // After use the card the type of selected tower will be the same item's type
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade amount resource of the selected Tower's
     * and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeResource() throws Exception {
        int amountBeforeUpgrade = mockedCurrentUsedTower.getResourceAmount();
        int expectedAmount = amountBeforeUpgrade + mockedUpgradeResourceItem.getImprovedAmountResource();
        inventoryService.upgradeTower(mockedUpgradeResourceItem, mockedCurrentUsedTower);
        assertEquals(expectedAmount, mockedCurrentUsedTower.getResourceAmount()); // After use the upgrade item the amount resource is added the item's incrementResource
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade the time (not smaller than 500ms)
     * of the selected Tower's and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeTimeAndNotSmallerThanLimitTime() throws Exception {
        long timeBeforeUpgrade = mockedCurrentUsedTower.getRecoveryTime();
        long expectedTime = (long) (timeBeforeUpgrade - mockedUpgradeTimeItem.getImprovedTime());
        inventoryService.upgradeTower(mockedUpgradeTimeItem, mockedCurrentUsedTower);
        long actualTime = mockedCurrentUsedTower.getRecoveryTime();
        assertEquals(expectedTime, actualTime); // After use the card, the recovery time of selected tower will be reduced
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade the time (smaller than 500ms)
     * will throw an Exception and won't remove from upgrade items list
     * @throws Exception
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeTimeAndSmallerThanLimitTime() throws Exception {
//        mockedCurrentUsedTower.setRecoveryTime(1000);
        int sizeUpgradeItemsBefore = inventoryService.getListUpgradeItemsInInventory().size();
        Exception exception = assertThrows(Exception.class, () -> inventoryService.upgradeTower(mockedUpgradeTimeItem, mockedCurrentUsedTower2));
        assertEquals("You can't upgrade time lower than 0.5 second", exception.getMessage());
        assertEquals(sizeUpgradeItemsBefore, inventoryService.getListUpgradeItemsInInventory().size());
    }
}
