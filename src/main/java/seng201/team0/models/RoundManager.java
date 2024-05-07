package seng201.team0.models;

import java.util.ArrayList;

/**
 * Round manager class to control aspect of each round in the game.
 */
public class RoundManager {
    private int currentRound = 1;   // This tracks the current round number
    private int numberOfRounds;     // Whereas this holds the number of rounds the user would like to play
    private float trackDistance;
    private ArrayList<Cart> carts;

    /**
     * Constructor for a RoundManager object
     * @param inputDistance Initial track distance
     * @param inputCarts Initial Array list of cart objects
     */
    public RoundManager(float inputDistance, ArrayList<Cart> inputCarts) {
        this.trackDistance = inputDistance;
        this.carts = inputCarts;
    }

    /**
     * If all the rounds have not been played yet, increment the current round counter.
     * @return (boolean)if the round has been incremented or not
     */
    public boolean nextRound() {
        if (currentRound < numberOfRounds) {
            currentRound++;
            return true;
        }
        currentRound = 1;
        return false;
    }

    /**
     * Gets the current round number
     * @return (int)current round number
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the number of rounds the player would like to play
     * @param numberOfRounds Number of rounds to be played
     */
    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    /**
     * Sets the cart track length
     * @param distance track distance
     */
    public void setTrackDistance(float distance) {
        trackDistance = distance;
    }
}
