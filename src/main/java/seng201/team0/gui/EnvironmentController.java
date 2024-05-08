package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.services.EnvironmentManager;

import java.io.IOException;

public class EnvironmentController {
    private String difficultyOfGame;
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new EnvironmentManager(this::launchSetupScreen, this::launchInventoryScreen, this::clearPane);
    }

    public void launchSetupScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupScreenController(environmentManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Manager Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchInventoryScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new InventoryController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Manager Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public  void launchRoundDifficultyScreen(EnvironmentManager environmentManager) {
//        try {
//            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_difficulty_screen.fxml"));
//            mainScreenLoader.setControllerFactory(param -> new DifficultyController(environmentManager));
//            Parent setupParent  = mainScreenLoader.load();
//            pane.getChildren().add(setupParent);
//            stage.setTitle("Round Manager Round Difficulty Screen");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void launchRoundScreen(EnvironmentManager towerManager) {
//        try {
//            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_game.fxml"));
//            mainScreenLoader.setControllerFactory(param -> new RoundController(environmentManager));
//            Parent setupParent  = mainScreenLoader.load();
//            pane.getChildren().add(setupParent);
//            stage.setTitle("Tower Manager Inventory Screen");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

