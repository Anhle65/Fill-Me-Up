package seng201.team0.gui;

import seng201.team0.models.RoundManager;

public class DifficultyController {
    private int easy = 1;
    private int moderate = 2;
    private int challenging = 3;
    private int currentDifficulty = easy;

    public void onChooseDifficultyEasy(){
        currentDifficulty = easy;
    }

    public void onChooseDifficultyModerate(){
        currentDifficulty = moderate;
    }

    public void onChooseDifficultyChallenging(){
        currentDifficulty = challenging;
    }

    /**
     * Sets up various game variables according to the current round counter and difficulty
     * on a given RoundManager object.
     * @param rm RoundManager object for game environment changes to be made on
     */
    public void roundDifficultySet(RoundManager rm) {

        if (currentDifficulty == easy) {
            // Set variables for easy mode
            rm.setTrackDistance(10);
        } else if (currentDifficulty == moderate) {
            // Set variables for moderate mode
            rm.setTrackDistance(8);
        } else if (currentDifficulty == challenging) {
            // Set variables for challenging mode
            rm.setTrackDistance(5);
        }
    }

}
