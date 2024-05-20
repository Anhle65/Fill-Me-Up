package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.services.EnvironmentManager;
import javafx.scene.control.Label;

public class LoserController {
    private EnvironmentManager environmentManager;

    public LoserController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;


    @FXML
    private void onExitGameButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onPlayAgainButtonClicked() {
        environmentManager.resetScore();
        environmentManager.closeCurrentScreen();
        environmentManager.launchSetupScreen();
    }

    public void initialize(){
        totalScoreLabel.setText(environmentManager.getScore() + " points");
        playerNameLabel.setText(environmentManager.getPlayerName());
        roundCompletedLabel.setText((environmentManager.getCurrentRoundNumber() - 1) + " round completed");

    }
}
