package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.services.EnvironmentManager;

public class WinnerNextRoundController {
    private EnvironmentManager environmentManager;


    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onNextRoundButtonClicked(){
        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds()); // This print the current round
        environmentManager.closeWinnerNextRoundScreen();
        environmentManager.launchRoundDifficultySelectScreen();
    }


    public WinnerNextRoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize(){}

}
