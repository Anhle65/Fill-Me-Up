package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.RandomEventService;

public class WinnerNextRoundController {
    private EnvironmentManager environmentManager;
    private RandomEventService randomEventService;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;

    @FXML
    private Label roundRemainingLabel;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public WinnerNextRoundController(EnvironmentManager environmentManager, RandomEventService randomEventService) {
        this.environmentManager = environmentManager;
        this.randomEventService = randomEventService;

    }

    public void initialize(){
        scoreLabel.setText(environmentManager.getScore() + " points");
        playerNameLabel.setText(environmentManager.getPlayerName() + " player");
        roundCompletedLabel.setText(environmentManager.getCurrentRoundNumber() + " round completed");
        roundRemainingLabel.setText(environmentManager.getNumberOfRounds() - environmentManager.getCurrentRoundNumber()+ " round remaining");
    }
    @FXML
    private void onNextRoundButtonClicked() {
        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds()); // This print the current round
        int currentRound = environmentManager.getCurrentRoundNumber();
//        if(currentRound > 1){
        this.randomEventService.dicePossibilityToHaveEvent();
        if (this.randomEventService.isHasRandomEvent()) {
            randomEventService.eventRemoveRandomTower();
            randomEventService.setHasRandomEventToFalse();
//            }
        }
        environmentManager.closeCurrentScreen();
        environmentManager.launchShopScreen();
    }
}
