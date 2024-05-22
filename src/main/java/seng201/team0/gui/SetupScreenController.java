package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SetupScreenController{
    @FXML
    private Label towerType;
    @FXML
    private Label recoveryTime;
    @FXML
    private Label resourceAmount;
    @FXML
    private Label levelTower;
    @FXML
    private Label towerCost;
    @FXML
    private TextField nameTextField;
    @FXML
    private Slider roundSlider;
    @FXML
    private Button selectedTower1;
    @FXML
    private Button selectedTower2;
    @FXML
    private Button selectedTower3;
    @FXML
    private Button towerStat1;
    @FXML
    private Button towerStat2;
    @FXML
    private Button towerStat3;
    @FXML
    private Button towerStat4;
    @FXML
    private Button towerStat5;
    @FXML
    private RadioButton easyGameRadioButton;
    @FXML
    private RadioButton moderateGameRadioButton;
    @FXML
    private RadioButton challengingGameRadioButton;


    @FXML
    private Label warningLabel;

    private boolean nameTextFieldEmpty = true;
    private boolean gameDifficultyEmpty = true;
    private boolean selectedTower1Empty = true;
    private boolean selectedTower2Empty = true;
    private boolean selectedTower3Empty = true;
    private Button selectDefaultTowerButton;
    private Button selectTowerButton;
    private List<Button> selectedTowerButtons;
    private List<Button> towerButtons;
    private boolean initialTowerSelectedEmpty = true;
    @FXML
    private EnvironmentManager environmentManager;
    private InventoryService inventoryService;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];;
    public SetupScreenController(EnvironmentManager environmentManager, InventoryService inventoryService){
        this.environmentManager = environmentManager;
        this.inventoryService = inventoryService;
    }
    @FXML
    private void onTower1ButtonClicked(){
        initialTowerSelectedEmpty = false;
    }

    @FXML
    private void onTower2ButtonClicked(){
        initialTowerSelectedEmpty = false;
    }

    @FXML
    private void onTower3ButtonClicked(){
        initialTowerSelectedEmpty = false;
    }
    @FXML
    private void onTower4ButtonClicked(){
        initialTowerSelectedEmpty = false;
    }
    @FXML
    private void onTower5ButtonClicked(){
        initialTowerSelectedEmpty = false;
    }

    @FXML
    private void onSelectedTower1ButtonClicked(){
        if (!initialTowerSelectedEmpty) {
            selectedTower1Empty = false;
        }
    }

    @FXML
    private void onSelectedTower2ButtonClicked(){
        if (!initialTowerSelectedEmpty) {
            selectedTower2Empty = false;
        }
    }

    @FXML
    private void onSelectedTower3ButtonClicked(){
        if (!initialTowerSelectedEmpty) {
            selectedTower3Empty = false;
        }
    }


    @FXML
    private void onEasyGameRadioButtonClicked(){
        easyGameRadioButton.setSelected(true);
        moderateGameRadioButton.setSelected(false);
        challengingGameRadioButton.setSelected(false);
        gameDifficultyEmpty = false;

        environmentManager.setGameDifficulty("Easy");

    }

    @FXML
    private void onModerateGameRadioButtonClicked(){
        moderateGameRadioButton.setSelected(true);
        easyGameRadioButton.setSelected(false);
        challengingGameRadioButton.setSelected(false);
        gameDifficultyEmpty = false;


        environmentManager.setGameDifficulty("Moderate");

    }

    @FXML
    private void onChallengingGameRadioButtonClicked(){
        challengingGameRadioButton.setSelected(true);
        easyGameRadioButton.setSelected(false);
        moderateGameRadioButton.setSelected(false);
        gameDifficultyEmpty = false;


        environmentManager.setGameDifficulty("Challenging");
    }

    @FXML
    private void onNameTextFieldChanged() {
        if (!Objects.equals(nameTextField.getText(), "") || !(nameTextField.getText().trim().isEmpty())){
            if((nameTextField.getText()).length() >= 3 && (nameTextField.getText()).length() <= 15)
                nameTextFieldEmpty = false;
        }
        System.out.println(nameTextField.getText());
    }
    @FXML
    private void onAcceptClicked() {
        onNameTextFieldChanged();
        if (nameTextFieldEmpty) {
            warningLabel.setText("Please enter your name from 3-15 characters!");
        }
        else if (!environmentManager.isNotSpecialChar(nameTextField.getText())){
            warningLabel.setText("Your name must not contain special characters!");
        }
        else if (gameDifficultyEmpty) {
            // Print error message
            warningLabel.setText("Please choose game difficulty!");
        }
        else {
            environmentManager.setName(nameTextField.getText());
            System.out.println("You chose: " + environmentManager.getGameDifficulty() + " option."); // Print the difficulty user choose
            environmentManager.setNumberOfRounds((int) roundSlider.getValue());
            System.out.println("You choose: " + environmentManager.getNumberOfRounds() + " rounds"); // Print number of rounds which user choose
            inventoryService.setCurrentTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
            environmentManager.closeCurrentScreen();
            environmentManager.launchRoundDifficultySelectScreen();
        }



    }
    @FXML
    private void onExitClicked() {
        System.exit(0);
    }

    @FXML
    private void onResetSelectedTowerClicked(){
        for(int i=0; i < this.selectedTowerButtons.size(); i++){
            this.selectedTowerButtons.get(i).setDisable(false);
            this.selectedTowerButtons.get(i).setText("");
        }
        for(int i=0; i < this.towerButtons.size(); i++){
            this.towerButtons.get(i).setDisable(false);
        }
    }

    /**
     * Initialize the setup page
     */

    public void initialize(){
        environmentManager.resetCurrentRoundNumber();
        this.selectedTowerButtons = List.of(selectedTower1, selectedTower2, selectedTower3);
        this.towerButtons = List.of(towerStat1, towerStat2, towerStat3, towerStat4, towerStat5);
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(inventoryService.getDefaultTowers().get(finalI));
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        this.selectDefaultTowerButton = button;
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-family: System; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
        for (int i = 0; i < selectedTowerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTowerIndex != -1) {
                    selectedTowerButtons.get(finalI).setText(inventoryService.getDefaultTowers().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = inventoryService.getDefaultTowers().get(selectedTowerIndex);
                    this.selectedTowerIndex = -1;
                    this.selectTowerButton = selectedTowerButtons.get(finalI);
                    this.selectTowerButton.setDisable(true);
                    this.selectDefaultTowerButton.setDisable(true);
                }
            });
        }
    }
    private void updateStats(Tower tower) {
        towerType.setText(tower.getName());
        towerCost.setText(String.valueOf(tower.getCost()));
        levelTower.setText(String.valueOf(tower.getLevel()));
        recoveryTime.setText(String.valueOf(tower.getRecoveryTime()/1000) + " s");
        resourceAmount.setText(String.valueOf(tower.getResourceAmount()));
    }
}
