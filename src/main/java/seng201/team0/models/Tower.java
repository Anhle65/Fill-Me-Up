package seng201.team0.models;

public class Tower {
    private String resourceType;
    private int level;
    private int cost;
    private boolean isWorking = true;
    private int resourceAmount;
    private float recoveryTime;
    boolean inUse = false;
    public Tower(String resourceType, int level, int cost, int resourceCapacity, float recoveryTime, boolean isWorking, boolean inUse){
        this.resourceType = resourceType;
        this.level = level;
        this.cost = cost;
        this.resourceAmount = resourceCapacity;
        this.recoveryTime = recoveryTime;
        this.isWorking = isWorking;
        this.inUse = inUse;
    }
    public int getCapacity(){ return resourceAmount;}
    public String getType(){ return resourceType;}
    public int getLevel(){ return level;}
    public int getCost(){ return cost;}
    public boolean isWorkingStatus(){ return isWorking;}
    public boolean isInUse(){ return inUse;}
    public float getRecoveryTime(){ return recoveryTime;}
    public void upgradeCapacity(int expand){ resourceAmount += expand; }
    public void upgradeTime(float time){
        if (recoveryTime - time >= 1)
            recoveryTime -= time;
    }
    public void changeTypeResource(String newType){ resourceType = newType;}
}
