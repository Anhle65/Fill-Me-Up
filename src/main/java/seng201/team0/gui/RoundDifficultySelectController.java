package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.models.Cart;
import seng201.team0.services.EnvironmentManager;


public class RoundDifficultySelectController {
    private String easy;
    private String moderate;
    private String challenging;
    private EnvironmentManager environmentManager;
    private Cart cart;

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
        environmentManager.closeCurrentScreen();
        environmentManager.launchInventoryScreen();
    }

    public void onBackButtonClicked() {
        environmentManager.closeCurrentScreen();
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
            environmentManager.closeCurrentScreen();
            environmentManager.launchEasyGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Moderate")) {
            environmentManager.closeCurrentScreen();
            environmentManager.launchModerateGameScreen();

        }
        if (environmentManager.getGameDifficulty().equals("Challenging")){
            environmentManager.closeCurrentScreen();
            environmentManager.launchChallengingGameScreen();

        }

    }

}
