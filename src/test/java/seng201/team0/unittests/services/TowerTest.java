package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerTest {
    private Tower tower;

    /**
     * Setup before each test, creating a mocked Tower object
     */
    @BeforeEach
    public void setupTest() {
        // Use CounterService directly
        tower = new Tower("water", 40, 15, 3000);
    }

    /**
     * Test incrementing the amount of resource of tower after update by incrementAmount
     */
    @Test
    void testUpgradeResourceAmount(){
        tower.upgradeResourceAmount(5);
        assertEquals(20, tower.getResourceAmount());
    }

    /**
     * Test type of Tower after using the changeTypeResource
     */
    @Test
    void testChangeTypeResource(){
        tower.changeTypeResource("fire");
        assertEquals("fire", tower.getName());
    }

    /**
     * Test incrementing the tower level by one
     */
    @Test
    void testLevelIncrement(){
        tower.levelIncrement();
        assertEquals(2,tower.getLevel());
    }

    @Test
    void testUpgradeTime(){
        tower.upgradeTime(2000);
        assertEquals(1000, tower.getRecoveryTime());
    }
}
