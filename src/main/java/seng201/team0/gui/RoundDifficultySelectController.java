package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.services.EnvironmentManager;

public class RoundDifficultySelectController {
    private String easy;
    private String moderate;
    private String challenging;
    private String roundDifficulty;
    private EnvironmentManager environmentManager;

    @FXML
    private RadioButton easyRadioButton;

    @FXML
    private RadioButton moderateRadioButton;

    @FXML
    private RadioButton challengingRadioButton;

    @FXML
    private Button playNowButton;

    @FXML
    private Label currentRoundLabel;

    public RoundDifficultySelectController(EnvironmentManager environmentManager) { this.environmentManager = environmentManager; }

    public void initialize(){
        currentRoundLabel.setText(String.valueOf(environmentManager.getCurrentRoundNumber()));
    }
    @FXML
    public void onOpenInventoryButtonClicked() {
        environmentManager.closeEasyGameScreen();
        environmentManager.launchInventoryScreen();
    }

    public void onBackButtonClicked() {
        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds()); // This print the current round
        environmentManager.closeEasyGameScreen();
        environmentManager.launchSetupScreen();
    }

    public void onEasyRadioButtonClicked() {
        easyRadioButton.setSelected(true);
        roundDifficulty = "Easy";
        moderateRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onModerateRadioButtonClicked() {
        moderateRadioButton.setSelected(true);
        roundDifficulty = "Moderate";
        easyRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onChallengingRadioButtonClicked() {
        challengingRadioButton.setSelected(true);
        roundDifficulty = "Challenging";
        easyRadioButton.setSelected(false);
        moderateRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }

    public void onPlayNowButtonClicked() {
        if (environmentManager.getGameDifficulty().equals("Easy")){
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchEasyGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Moderate")) {
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchModerateGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Challenging")){
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchChallengingGameScreen();

        }

    }

}
