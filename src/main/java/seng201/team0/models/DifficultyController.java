package seng201.team0.models;

public class DifficultyController {
    private int easy = 1;
    private int moderate = 2;
    private int challenging = 3;
    private int currentDifficulty = easy;
    private int levelCounter = 1;

    public void onChooseDifficultyEasy(){
        currentDifficulty = easy;
    }

    public void onChooseDifficultyModerate(){
        currentDifficulty = moderate;
    }

    public void onChooseDifficultyChallenging(){
        currentDifficulty = challenging;
    }

    public void IncrementLevel() {
        if (currentDifficulty == easy) {
            levelCounter += 1;
        } else if (currentDifficulty == moderate) {
            levelCounter += 2;
        } else {
            levelCounter += 3;
        }
    }
    public int getLevelCounter() {
        return levelCounter;
    }

    /* Sets up various game variables according to the current round counter and difficulty
       on a given RoundManager object
    * */
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
