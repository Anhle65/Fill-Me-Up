package seng201.team0.models;

public class Cart {
    private final String typeResourceCart;
    private final float speed;
    private final int sizeOfCart;
    private int currentAmount = 0;
    public Cart(String inputResoure, float inputSpeed, int inputSize ) {
        this.typeResourceCart = inputResoure;
        this.speed = inputSpeed;
        this.sizeOfCart = inputSize;
    }

    /**
     * Get the type of cart
     * @return typeResourceCart
     */
    public String getTypeResourceCart(){return this.typeResourceCart;}

    /**
     * Get the size of cart
     * @return sizeOfCart
     */
    public int getSizeOfCart(){return this.sizeOfCart;}

    /**
     * Get the speed of cart
     * @return speed
     */
    public float getSpeed(){return this.speed;}

    /**
     * Increment the amount resource into cart if the input is the same type with cart's type
     */
    public void incrementAmountResourceIntoCart(Tower tower) {
        if(tower.getType().equals(this.typeResourceCart)){
            currentAmount += tower.getResourceAmount();
            if(currentAmount > sizeOfCart)
                currentAmount = sizeOfCart;
        }
    }

    /**
     * Get the current amount that cart has been filled
     * @return currentAmount
     */
    public int getCurrentAmountOfCart(){return currentAmount;}

    /**
     * Check if the cart has been fulled or not
     * @return true if the cart has been filled up, false otherwise
     */
    public boolean isCartFilledUp(){return currentAmount == sizeOfCart;}
}
