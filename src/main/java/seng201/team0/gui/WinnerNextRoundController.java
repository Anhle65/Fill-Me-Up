package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;

public class WinnerNextRoundController {
    private EnvironmentManager environmentManager;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onNextRoundButtonClicked(){
        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds()); // This print the current round
        environmentManager.closeCurrentScreen();
        environmentManager.launchRoundDifficultySelectScreen();
    }

    public WinnerNextRoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize(){
        scoreLabel.setText(environmentManager.getScore() + " points");
        playerNameLabel.setText(environmentManager.getPlayerName());
        roundCompletedLabel.setText(environmentManager.getCurrentRoundNumber() + " in total");

    }

}
