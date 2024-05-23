package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.TowerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEventTest {
    private TowerService towerService;
    private RandomEvent randomEvent;
    private InventoryService inventoryService;
    private int randomEventHappened = 1;
    private int randomEventNotHappened = 2;
    @BeforeEach
    public void setUpTest(){
        towerService = new TowerService();
        inventoryService = new InventoryService(towerService);
        randomEvent = new RandomEvent(inventoryService);
        List<Tower> listCurrentTowers = new ArrayList<Tower>();
        Tower mockedTower1 = new Tower("Water", 20, 20, 2000);
        Tower mockedTower2 = new Tower("Water", 20, 20, 2000);
        mockedTower1.setInUseState(true);
        listCurrentTowers.add(mockedTower1);
        listCurrentTowers.add(mockedTower2);
        listCurrentTowers.add(mockedTower1);
        listCurrentTowers.add(mockedTower2);
        inventoryService.setCurrentTowerList(listCurrentTowers);
    }

    @Test
    void testDrawPossibilityToHaveEventIsEqualToOne(){
        randomEvent.isHasRandomEvent();
    }
}
