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
        environmentManager.closeEasyGameScreen();
        environmentManager.launchSetupScreen();
    }

    public void onEasyRadioButtonClicked() {
        easyRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Easy");
        moderateRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onModerateRadioButtonClicked() {
        moderateRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Moderate");
        easyRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onChallengingRadioButtonClicked() {
        challengingRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Challenging");
        easyRadioButton.setSelected(false);
        moderateRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }

    public void onPlayNowButtonClicked() {
        if (environmentManager.getGameDifficulty().equals("Easy")){
            System.out.println("Playing Easy mode");
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchEasyGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Moderate")) {
            System.out.println("Playing Moderate mode");
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchModerateGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Challenging")){
            System.out.println("Playing Challenging mode");
            environmentManager.closeRoundDifficultySelectScreen();
            environmentManager.launchChallengingGameScreen();

        }

    }

}
