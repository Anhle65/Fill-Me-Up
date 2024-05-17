package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.services.EnvironmentManager;

public class LoserController {
    private EnvironmentManager environmentManager;

    public LoserController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    @FXML
    private void onExitGameButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onPlayAgainButtonClicked() {
        environmentManager.closeLoserScreen();
        environmentManager.launchSetupScreen();
    }
}
