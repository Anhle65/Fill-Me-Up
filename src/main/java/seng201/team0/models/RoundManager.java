package seng201.team0.models;

import javafx.animation.TranslateTransition;
import seng201.team0.services.EnvironmentManager;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Round manager class to control aspect of each round in the game.
 */
public class RoundManager {
    private int currentRound = 1;   // This tracks the current round number
    private int numberOfRounds;     // Whereas this holds the number of rounds the user would like to play
    private int trackDistance;
    private ArrayList<Cart> carts;
    private EnvironmentManager environmentManager;

    /**
     * Constructor for a RoundManager object
     *
     * @param environmentManager environment manager object for accessing environment variable
     */
    public RoundManager(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    /**
     * If all the rounds have not been played yet, increment the current round counter.
     *
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
     *
     * @return (int)current round number
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the number of rounds the player would like to play
     *
     * @param numberOfRounds Number of rounds to be played
     */
    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    /**
     * get the cart track length depends on player choice of game difficulty
     */

    public int getTrackDistance() {
        if (environmentManager.getGameDifficulty().equals("Easy")) {
            this.trackDistance = 10;
        } else if (environmentManager.getGameDifficulty().equals("Moderate")) {
            this.trackDistance = 8;
        } else if (environmentManager.getGameDifficulty().equals("Challenging")) {
            this.trackDistance = 5;
        }
        return trackDistance;
    }

    public int getNumberOfCarts() {
        return carts.size();
    }

}