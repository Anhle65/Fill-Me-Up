package seng201.team0.models;

public class DifficultyController {
    int Easy = 1;
    int Moderate = 2;
    int Challenging = 3;
    int CurrentDifficulty = Easy;
    public void onChooseDifficultyEasy(){
        CurrentDifficulty = Easy;
    }

    public void onChooseDifficultyModerate(){
        CurrentDifficulty = Moderate;
    }

    public void onChooseDifficultyChallenging(){
        CurrentDifficulty = Challenging;
    }
}
