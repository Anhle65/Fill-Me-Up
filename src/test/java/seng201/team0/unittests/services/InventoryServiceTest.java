package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class InventoryServiceTest {
    private InventoryService inventoryService;
    private Tower mockedReservedTower;
    private Tower mockedCurrentUsedTower;

    /**
     * Set up before each test set playerCoin, create mockedReservedTower, mockedCurrentUsedTower, Inventory instance
     */
    @BeforeEach
    public void setUpTest(){
        inventoryService = new InventoryService();
        mockedReservedTower = new Tower("Water", 30, 20, 3000);
        mockedCurrentUsedTower = new Tower("Fire", 20, 20, 3000);
        inventoryService.setPlayerCoins(30);
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
        int sizeCurrentTowerList = inventoryService.getCurrentTowerList().size();
        assertEquals(50, inventoryService.getPlayerCoins());
        assertEquals(0, sizeCurrentTowerList);
    }
}
