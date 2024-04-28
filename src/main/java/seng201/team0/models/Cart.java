package seng201.team0.models;

public class Cart {
    private String typeResource;
    private float speed;
    private int size;
    private int currentAmount = 0;
    private int amount;
    public Cart(String inputResoure, float inputSpeed, int inputSize ) {
        this.typeResource = inputResoure;
        this.speed = inputSpeed;
        this.size = inputSize;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){return amount;}
    public void incrementResource() {
        currentAmount += getAmount();
    }
    public int getCurrentSize(){return currentAmount;}
}
