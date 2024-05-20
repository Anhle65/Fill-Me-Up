package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;

public class WinnerGameController {
    private EnvironmentManager environmentManager;

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onNewGameButtonClicked() {
        environmentManager.closeWinnerGameScreen();
        environmentManager.launchSetupScreen();
    }

    public WinnerGameController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize(){
        totalScoreLabel.setText(environmentManager.getScore() + " points");
    }
}
