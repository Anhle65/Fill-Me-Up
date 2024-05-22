package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.team0.models.UpgradeItems;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;
import seng201.team0.models.UpgradeItems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private ShopService shopService;
    private InventoryService inventoryService;
    private int currentCoin;
   private String nameItem;
    /**
     * Initialize inventory service and set current coin
     */
    @BeforeEach
    public void setUpTest(){
        inventoryService = new InventoryService();
        shopService = new ShopService(inventoryService);
        inventoryService.setPlayerCoins(150);

    }

    /**
     * Test the buy button functionality which added the upgrade items type item if
     * player have current coins enough or more than item's cost
     */
    @Test
    public void testBuyUpgradedItemSuccess(){
        UpgradeItems item = new UpgradeItems(nameItem, 0, 0,20);

    }


}
