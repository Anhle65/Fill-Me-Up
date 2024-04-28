package seng201.team0.models;

public class Cart {
    private String typeResource;
    private float speed;
    private int size;

    public Cart(String inputResoure, float inputSpeed, int inputSize ) {
        this.typeResource = inputResoure;
        this.speed = inputSpeed;
        this.size = inputSize;
    }

}
