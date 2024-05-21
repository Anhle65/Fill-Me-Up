package seng201.team0.gui;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import seng201.team0.services.InventoryService;


public class EasyGameController {
    private EnvironmentManager environmentManager;

    @FXML
    private ImageView cartImageView;

    @FXML
    private Label resourceLabel;

    @FXML
    private Button tower1Button;

    @FXML
    private Button tower2Button;

    @FXML
    private Button tower3Button;

    @FXML
    private Button tower4Button;

    @FXML
    private Button tower5Button;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resumeButton;

    @FXML
    private Button selectedTowerButton;

    @FXML
    private ProgressBar progressCart1;

    @FXML
    float progress;
    private Tower selectedTower;
    private List<Cart> listCartsInRound;
    private Cart selectedCart;
    private boolean isFull = false;
    private List<ImageView> view = new ArrayList<>();
    private int roundDifficultySpeed = 0;
    private InventoryService inventoryService;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public EasyGameController(EnvironmentManager environmentManager, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.inventoryService = inventoryService;
    }

    // TODO: As this game easy mode only has one cart, we can remove lists and looping over them as it is not needed. (Will still use this way for moderate and challenging)
    public void initialize() {
        if (environmentManager.getRoundDifficulty().equals("Easy")) {roundDifficultySpeed = 80;}
        else if (environmentManager.getRoundDifficulty().equals("Moderate")) {roundDifficultySpeed = 100;}
        else if (environmentManager.getRoundDifficulty().equals("Challenging")) {roundDifficultySpeed = 120;}

        long cartSpeed = roundDifficultySpeed + ((long)environmentManager.getCurrentRoundNumber() * 20);

        Cart cart = new Cart(inventoryService.getCurrentTowerList().get(0).getName(), cartSpeed, 100);
        System.out.println(cart.getTypeResourceCart());
        listCartsInRound = List.of(cart);
        List<ImageView> listImageView = List.of(cartImageView);
        for (int i = 0; i < listImageView.size(); i++) {
            int finalI = i;
            listImageView.get(finalI).setOnMouseClicked(mouseEvent -> {
                selectedCart = listCartsInRound.get(finalI);
                if (selectedTower != null) {
                    selectedCart.incrementAmountResourceIntoCart(selectedTower);
                    if (selectedCart.getIsIncrementIntoCart()) {
                        progress += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                        progressCart1.setProgress(progress);
                        selectedCart.setIncrementIntoCartToFalse();
                        this.isFull = selectedCart.isCartFilledUp();
                    }
                    selectedTower = null;
                }
            });
        }

        List<Button> listTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        TranslateTransition translateButton1 = new TranslateTransition();
        TranslateTransition translateButton2 = new TranslateTransition();
        TranslateTransition translateButton3 = new TranslateTransition();
        TranslateTransition translateButton4 = new TranslateTransition();
        TranslateTransition translateButton5 = new TranslateTransition();
        List<TranslateTransition> translateButtons = List.of(translateButton1, translateButton2, translateButton3, translateButton4, translateButton5);
        for (int i = 0; i < inventoryService.getCurrentTowerList().size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            listTowerButtons.get(finalI).setText(inventoryService.getCurrentTowerList().get(finalI).getName());
            listTowerButtons.get(i).setOnAction(event -> {
                listTowerButtons.forEach(button -> {
                    if (button == listTowerButtons.get(finalI)) {
                        this.selectedTower = inventoryService.getCurrentTowerList().get(finalI);
                        long time = selectedTower.getRecoveryTime();
                        translateButtons.get(finalI).setNode(listTowerButtons.get(finalI));
                        translateButtons.get(finalI).setDuration(Duration.millis(time));
                        button.setDisable(true);
                        translateButtons.get(finalI).setOnFinished(actionEvent -> {
                            button.setDisable(false);
                        });
                        translateButtons.get(finalI).play();
                        button.setStyle("-fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        selectedCart = listCartsInRound.get(0);

        this.selectedCart.generateAnimation(cartImageView, progressCart1, resourceLabel).setOnFinished(actionEvent -> {
            System.out.println("End game");
            if (isFull) {
                if (environmentManager.getRoundDifficulty().equals("Easy")) {
                    environmentManager.incrementScore(10);
                }
                else if (environmentManager.getRoundDifficulty().equals("Moderate")) {
                    environmentManager.incrementScore(12);
                }
                else if (environmentManager.getRoundDifficulty().equals("Challenging")) {
                    environmentManager.incrementScore(15);
                }
                if (environmentManager.getCurrentRoundNumber() != environmentManager.getNumberOfRounds()) {
                    environmentManager.closeCurrentScreen();
                    environmentManager.launchWinnerNextRoundScreen();
                } else if (environmentManager.getCurrentRoundNumber() == environmentManager.getNumberOfRounds()) {
                    environmentManager.closeCurrentScreen();
                    environmentManager.launchWinnerGameScreen();
                }
            }
            else {
                environmentManager.closeCurrentScreen();
                environmentManager.launchLoserScreen();
            }
        });
        selectedCart.startAnimation();
        }
    }





