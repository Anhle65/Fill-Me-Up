package seng201.team0.models;

import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class will do a random event
 */
public class RandomEvent {
    private InventoryService inventoryService;
    private final List<Integer> indexesTowerWillBeChosen = new ArrayList<Integer>();
    private Random randomEvent = new Random(400);
    private Random randomEventHappened = new Random(500);
    private boolean hasRandomEvent = false;

    /**
     * Constructor to create RandomEvent Instance
     * @param inventoryService InventoryService
     */
    public RandomEvent(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }
    public boolean isHasRandomEvent(){return this.hasRandomEvent;}

    /**
     * Random a number if equal to the fixed number is set, the event will happen
     */
    public void dicePossibilityToHaveEvent(){
        int randomEventHappenedNumber = randomEventHappened.nextInt(3);
        if(randomEventHappenedNumber == 1){
            this.hasRandomEvent = true;
        }
    }

    /**
     * Event occurs will remove a tower from current used towers list in inventory
     * if towers is used in previous round, the more chance it will be chosen to removed
     */
    public void eventRemoveRandomTower(){
        List<Tower> currentTowers = inventoryService.getCurrentUsedTowerList();
        for(int i=0; i < currentTowers.size(); ++i){
            Tower tower = currentTowers.get(i);
            if(tower.isInUse()) {
                indexesTowerWillBeChosen.add(i);
                tower.setInUseState(false);
            }
            indexesTowerWillBeChosen.add(i);
        }
        int randomEventIndex = randomEvent.nextInt(indexesTowerWillBeChosen.size());
        Tower removedTower = currentTowers.get(randomEventIndex);
        currentTowers.remove(removedTower);
        inventoryService.setCurrentTowerList(currentTowers);
    }
}
