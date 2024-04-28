package seng201.team0.models;

import java.util.ArrayList;

public class RoundManager {
    private float trackDistance;
    private ArrayList<Cart> carts;

    public RoundManager(float inputDistance, ArrayList<Cart> inputCarts) {
        this.trackDistance = inputDistance;
        this.carts = inputCarts;
    }

    public void setTrackDistance(float distance) {
        trackDistance = distance;
    }
}
