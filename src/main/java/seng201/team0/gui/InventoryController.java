package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.List;

public class InventoryController {
    @FXML
    private Label playerName;
    @FXML
    private Label tower1Time;
    @FXML
    private Label tower2Time;
    @FXML
    private Label tower3Time;
    @FXML
    private Label tower4Time;
    @FXML
    private Label tower5Time;
    @FXML
    private Label tower1Resource;
    @FXML
    private Label tower2Resource;
    @FXML
    private Label tower3Resource;
    @FXML
    private Label tower4Resource;
    @FXML
    private Label tower5Resource;
    @FXML
    private Label tower1Level;
    @FXML
    private Label tower2Level;
    @FXML
    private Label tower3Level;
    @FXML
    private Label tower4Level;
    @FXML
    private Label tower5Level;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    @FXML
    private Button tower4;
    @FXML
    private Button tower5;
    private int selectedTowerIndex = -1;
    private Tower selectedTowers;
    private EnvironmentManager environmentManager;
    private List<Label> towerTime;
    private List<Label> towerResource;
    private List<Label> towerLevel;

    public InventoryController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    @FXML
    public void initialize() {
        this.playerName.setText(this.environmentManager.getName());
        this.towerTime = List.of(this.tower1Time, this.tower2Time, this.tower3Time, this.tower4Time, this.tower5Time);
        this.towerResource = List.of(this.tower1Resource, this.tower2Resource, this.tower3Resource, this.tower4Resource, this.tower5Resource);
        this.towerLevel = List.of(this.tower1Level, this.tower2Level, this.tower3Level, this.tower4Level, this.tower5Level);
        List<Button> towerButtons = List.of(this.tower1, this.tower2, this.tower3, this.tower4, this.tower5);

        for(int i = 0; i < this.environmentManager.getTowerList().size(); ++i) {
            int finalI = i;
            ((Button)towerButtons.get(i)).setOnAction((event) -> {
                this.updateStats((Tower)this.environmentManager.getTowerList().get(finalI), (Label)this.towerTime.get(finalI), (Label)this.towerResource.get(finalI), (Label)this.towerLevel.get(finalI));
                this.selectedTowerIndex = finalI;
                towerButtons.forEach((button) -> {
                    ((Button)towerButtons.get(finalI)).setText(String.valueOf(((Tower)this.environmentManager.getTowerList().get(finalI)).getType()));
                    if (button == towerButtons.get(finalI)) {
                        this.selectedTowers = (Tower) this.environmentManager.getTowerList().get(finalI);
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

    private void updateStats(Tower tower, Label towerTime, Label towerResource, Label towerLevel) {
        towerTime.setText(String.valueOf(tower.getRecoveryTime()));
        towerResource.setText(String.valueOf(tower.getResourceAmount()));
        towerLevel.setText(String.valueOf(tower.getLevel()));
    }

    @FXML
    private void onQuitClicked() {
        this.environmentManager.closeInventoryScreen();
    }

    @FXML
    private void onSwappedClicked() {
        System.out.println("onSwappedClicked");
//        this.selectedTowers.setCleanliness("Sparkling");
//        this.updateStats(this.selectedRockets, (Label)this.rocketNames.get(this.selectedRocketIndex), (Label)this.rocketFuels.get(this.selectedRocketIndex), (Label)this.rocketStates.get(this.selectedRocketIndex));
    }

    @FXML
    private void onUpgradedClicked() {
        System.out.println("onUpgradedClicked");
//        this.selectedRockets.setFuel("Full");
//        this.updateStats(this.selectedRockets, (Label)this.rocketNames.get(this.selectedRocketIndex), (Label)this.rocketFuels.get(this.selectedRocketIndex), (Label)this.rocketStates.get(this.selectedRocketIndex));
    }

    @FXML
    private void onReturnedClicked(){
        environmentManager.returnedSetupScreen();
    }
}