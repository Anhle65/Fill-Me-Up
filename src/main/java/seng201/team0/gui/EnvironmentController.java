package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.services.ShopService;
import seng201.team0.services.EnvironmentManager;

import java.io.IOException;

public class EnvironmentController {
    private String difficultyOfGame;
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new EnvironmentManager(this::launchSetupScreen, this::launchInventoryScreen, this::launchRoundDifficultySelectScreen, this::launchRoundGameScreen, this::launchWinnerNextRoundScreen, this::launchLoserScreen, this::launchShopScreen, this::launchModerateGameScreen, this::launchChallengingGameScreen, this::clearPane);
//        new Shop(this:: launchShopScreen);
    }

    public void launchSetupScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupScreenController(environmentManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Set Up Screen");
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
    public  void launchRoundDifficultySelectScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_difficulty_select_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RoundDifficultySelectController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Difficulty Select Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchRoundGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/easy_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new EasyGameController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Easy Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchWinnerNextRoundScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/winner_next_round_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new WinnerNextRoundController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Winner Next Round Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchLoserScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/loser_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new LoserController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Loser Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchShopScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopController(environmentManager, new ShopService()));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchModerateGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/moderate_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ModerateGameController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Moderate Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchChallengingGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/challenging_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ChallengingGameController(environmentManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Challenging Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

