package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

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
    private Button towerStat6;
    @FXML
    private ChoiceBox<String> difficultyOptions = new ChoiceBox<>();
    private EnvironmentManager environmentManager;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    public SetupScreenController(EnvironmentManager environmentManager){
        this.environmentManager = environmentManager;
    }
    @FXML
    private void onAcceptClicked() {
//        while (nameTextField.getText().length() < 5 || nameTextField.getText().length() > 15){
//            environmentManager.setName("Sorry, your name must be between 5-15 long");
//        }
        environmentManager.setName(nameTextField.getText());
        System.out.println("You chose: " + environmentManager.getGameDifficulty() + " option."); // Print the difficulty user choose
        environmentManager.setNumberOfRounds((int)roundSlider.getValue());
        System.out.println("You choose: "+environmentManager.getNumberOfRounds()+" rounds"); // Print number of rounds which user choose
        environmentManager.setCurrentTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
        environmentManager.closeSetupScreen();
    }



    /**
     * Initialize the setup page
     */

    public void initialize(){
        difficultyOptions.getItems().addAll("Easy", "Moderate", "Challenge");
        difficultyOptions.setValue("Choose the difficulty");
        difficultyOptions.setOnAction((event) -> {
            environmentManager.setGameDifficulty(difficultyOptions.getValue()); // Saved the selection of difficulty
        });
        List<Button> selectedTowerButtons = List.of(selectedTower1, selectedTower2, selectedTower3);
        List<Button> towerButtons = List.of(towerStat1, towerStat2, towerStat3, towerStat4, towerStat5, towerStat6);
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(environmentManager.getDefaultTowers().get(finalI));
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
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
                    selectedTowerButtons.get(finalI).setText(environmentManager.getDefaultTowers().get(selectedTowerIndex).getType());
                    selectedTowers[finalI] = environmentManager.getDefaultTowers().get(selectedTowerIndex);
                }
            });
        }
    }
    private void updateStats(Tower tower) {
        towerType.setText(tower.getType());
        towerCost.setText(String.valueOf(tower.getCost()));
        levelTower.setText(String.valueOf(tower.getLevel()));
        recoveryTime.setText(String.valueOf(tower.getRecoveryTime()));
        resourceAmount.setText(String.valueOf(tower.getResourceAmount()));
    }
}
