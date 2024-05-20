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

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public EasyGameController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    // TODO: As this game easy mode only has one cart, we can remove lists and looping over them as it is not needed. (Will still use this way for moderate and challenging)
    public void initialize() {

        Cart cart = new Cart(environmentManager.getCurrentTowerList().get(0).getName(), 115, 100);
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
                    System.out.println("Mouse event " + selectedCart.getTypeResourceCart() + " " + selectedCart.getCurrentAmountOfCart());
                }
            });
        }



        List<Button> listTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);


        for (int i = 0; i < environmentManager.getCurrentTowerList().size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            listTowerButtons.get(finalI).setText(environmentManager.getCurrentTowerList().get(finalI).getName());
            listTowerButtons.get(i).setOnAction(event -> {
//                selectedTowerIndex = finalI;
                listTowerButtons.forEach(button -> {
                    if (button == listTowerButtons.get(finalI)) {
                        selectedTowerButton = button;
                        this.selectedTower = environmentManager.getCurrentTowerList().get(finalI);
                        long time = selectedTower.getRecoveryTime();
                        TranslateTransition translateButton = new TranslateTransition();
                        translateButton.setNode(selectedTowerButton);
                        translateButton.setDuration(Duration.millis(time));
                        translateButton.setOnFinished(actionEvent -> {
                            selectedTowerButton.setDisable(false);
                        });
                        selectedTowerButton.setDisable(true);
                        translateButton.play();
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





