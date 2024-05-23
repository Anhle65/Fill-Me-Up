package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;

public class WinnerGameController {
    private EnvironmentManager environmentManager;

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onNewGameButtonClicked() {
        environmentManager.resetScore();
        environmentManager.closeCurrentScreen();
        environmentManager.launchSetupScreen();

    }

    public WinnerGameController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize(){
        totalScoreLabel.setText(environmentManager.getScore() + " points");
        playerNameLabel.setText(environmentManager.getPlayerName() + " player");
        roundCompletedLabel.setText(environmentManager.getCurrentRoundNumber() + " round completed" );

    }
}
