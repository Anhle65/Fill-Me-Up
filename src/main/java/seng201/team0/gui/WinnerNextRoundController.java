package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.RandomEventService;
import seng201.team0.models.Tower;


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
    private Label brokenTowerAlertLabel;

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

        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds());
        int currentRound = environmentManager.getCurrentRoundNumber();
        this.randomEventService.dicePossibilityToHaveEvent();
        if (this.randomEventService.isHasRandomEvent()) {
            Tower removedTower = randomEventService.eventRemoveRandomTower();
            brokenTowerAlertLabel.setText("OH NO! Your " + removedTower.getName() + " Tower has broken! You can no longer use it");
            randomEventService.setHasRandomEventToFalse();
        }
    }
    @FXML
    private void onNextRoundButtonClicked() {
        environmentManager.closeCurrentScreen();
        environmentManager.launchShopScreen();
    }
}
