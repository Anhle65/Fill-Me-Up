package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng201.team0.models.Tower;
import seng201.team0.services.TowerManager;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SetupScreenController {
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
    private TowerManager towerManager;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    public SetupScreenController(TowerManager tower){
        this.towerManager = tower;
    }
    @FXML
    private void onAcceptClicked() {
        towerManager.setName(nameTextField.getText());
        towerManager.setTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
        towerManager.closeSetupScreen();
    }
    public void initialize(Stage stage){
        List<Button> selectedTowerButtons = List.of(selectedTower1, selectedTower2, selectedTower3);
        List<Button> towerButtons = List.of(towerStat1, towerStat2, towerStat3, towerStat4, towerStat5, towerStat6);
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(towerManager.getDefaultTowers().get(finalI));
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
                    selectedTowerButtons.get(finalI).setText(towerManager.getDefaultTowers().get(selectedTowerIndex).getType());
                    selectedTowers[finalI] = towerManager.getDefaultTowers().get(selectedTowerIndex);
                }
            });
        }
//        roundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            for (int i = 0; i < selectedRocketButtons.size(); i++) {
//                selectedRocketButtons.get(i).setDisable(i >= newValue.intValue());
//            }
//        });
    }
    private void updateStats(Tower tower) {
        towerType.setText(tower.getType());
        towerCost.setText(String.valueOf(tower.getCost()));
        levelTower.setText(String.valueOf(tower.getLevel()));
        recoveryTime.setText(String.valueOf(tower.getRecoveryTime()));
        resourceAmount.setText(String.valueOf(tower.getCapacity()));
    }
}
