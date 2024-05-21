package seng201.team0.models;
/**
 * Tower class which will be interacted by user in game
 */
public class Tower extends PurchasableItem {
    private String resourceType;
    private int level;
    private int cost;
    private boolean isWorking;
    private int resourceAmount;
    private long recoveryTime;
    boolean inUse;
    /**
     * This constructor will create tower by the different input paras(resourceType, level, price, resourceAmount)
     */
    public Tower(String resourceType, int cost, int resourceAmount, long recoveryTime){
        this.resourceType = resourceType;
        this.level = 1;
        this.cost = cost;
        this.resourceAmount = resourceAmount;
        this.recoveryTime = recoveryTime;
        this.isWorking = true;
        this.inUse = false;
    }

    /**
     * Get the current amount of resource of the tower
     * @return Current (int)amountResource
     */
    public int getResourceAmount(){ return resourceAmount;}

    /**
     * @param incrementAmount
     * Increment the resource amount of the tower by expand
     */
    public void upgradeResourceAmount(int incrementAmount){ resourceAmount += incrementAmount; }

    /**
     * Get the current type of the tower
     * @return Current (String)type
     */
    public String getName(){ return resourceType;}

    /**
     * @param newType
     * Change the resource type of the tower into newType
     */
    public void changeTypeResource(String newType){ resourceType = newType;}

    /**
     * Get the current level of the tower
     * @return Current (int)level
     */
    public int getLevel(){ return level;}

    /**
     * Increment the level tower whenever it is upgraded by one
     */
    public void levelIncrement(){
        this.level += 1;
    }

    /**
     * Get the price of the tower
     * @return price of tower in float
     */
    public int getCost(){ return cost;}

    /**
     * Get the current available state of the tower
     * @return true if the tower is working OR false if the tower is broken
     */
    public boolean isWorkingStatus(){ return isWorking;}

    /**
     * @param isWorking
     * Set the property this.isWorking of tower to the isWorking input
     */
    public void setWorkingStatus(boolean isWorking){ this.isWorking = isWorking;}

    /**
     * Get the current place (in used OR in reserved) of the tower
     * @return true if the tower is using in round game OR false if the tower is in reserved
     */
    public boolean isInUse(){ return inUse;}

    /**
     * @param isUsed
     * Set the property this.inUse of tower to the isUsed input
     */
    public void setInUseState(boolean isUsed){ this.inUse = isUsed;}

    /**
     * Get the current recovery time after each action
     * @return current recovery time in long
     */
    public long getRecoveryTime(){ return recoveryTime;}

    /**
     * @param decrementTime
     * Decrement the recovery time of the tower by decrementTime
     */
    public void upgradeTime(float decrementTime) throws Exception{
        if (recoveryTime - decrementTime >= 500)
            recoveryTime -= decrementTime;
        else
            throw new Exception("You can't upgrade time lower than 0.5 second");
    }
}
