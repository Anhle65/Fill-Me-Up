package seng201.team0.models;

public class DifficultyController {
    private int Easy = 1;
    private int Moderate = 2;
    private int Challenging = 3;
    private int CurrentDifficulty = Easy;
    private int LevelCounter = 1;

    public void onChooseDifficultyEasy(){
        CurrentDifficulty = Easy;

    }

    public void onChooseDifficultyModerate(){
        CurrentDifficulty = Moderate;
    }

    public void onChooseDifficultyChallenging(){
        CurrentDifficulty = Challenging;
    }

    public void IncrementLevel() {
        if (CurrentDifficulty == Easy) {
            LevelCounter += 1;
        } else if (CurrentDifficulty == Moderate) {
            LevelCounter += 2;
        } else {
            LevelCounter += 3;
        }
    }
    public int getLevelCounter() {
        return LevelCounter;
    }

}
