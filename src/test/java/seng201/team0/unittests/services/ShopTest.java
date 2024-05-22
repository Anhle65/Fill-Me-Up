package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private ShopService shopService;
    private InventoryService inventoryService;
    private int currentCoin;
    private String nameItem;
    private float improvedTime = 0;
    private int improvedAmountResource = 0;
    private Tower tower1;
    private Tower tower2;
    /**
     * Initialize inventory service and set current coin
     */
    @BeforeEach
    public void setUpTest(){
        inventoryService = new InventoryService();
        shopService = new ShopService(inventoryService);
        inventoryService.setPlayerCoins(30);
        tower1 = new Tower("Water", 20, 20, 2000);
        tower2 = new Tower("Ruby", 20, 40, 2000);
    }

    /**
     * Test the buy button functionality which added the upgrade items type item if
     * player have current coins enough or more than item's cost
     */
    @Test
    public void testBuyUpgradedItemSuccess(){
        UpgradeItems item1 = new UpgradeItems(nameItem,improvedAmountResource,improvedTime,20);
        shopService.buy(item1);
        assertEquals(10, inventoryService.getPlayerCoins());
        UpgradeItems items2 = new UpgradeItems(nameItem,improvedAmountResource,improvedTime,30);
        shopService.buy(items2);
        assertEquals(0, inventoryService.getPlayerCoins());
    }
    /**
     * Test the buy button functionality which added the upgrade items type item if
     * player have current coins less than item's cost
     */
    @Test
    public void testBuyUpgradeItemFailed(){
        UpgradeItems item = new UpgradeItems(nameItem, improvedAmountResource, improvedTime, 35);
        assertFalse(inventoryService.getListUpgradeItemsInInventory().contains(item));
    }

    /** Test the buy button functionality which added the Tower type item if
     * player have current coins enough or more than item's cost
     */
    public void testBuyTowerItemSuccess() {
        shopService.buy(tower1);
        assertEquals(0, inventoryService.getPlayerCoins());
    }

    /** Test the buy button functionality which added the Tower type item if
     * player have current coins less than item's cost
     */
    public void testBuyTowerItemFailed() {
        shopService.buy(tower2);
        assertFalse(inventoryService.getReservedTowerList().contains(tower2));
    }







}
