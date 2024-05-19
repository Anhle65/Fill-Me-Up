package seng201.team0.gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.models.RoundManager;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;


public class ChallengingGameController {
    private EnvironmentManager environmentManager;
    private int selectedTowerIndex = -1;

    @FXML
    private ImageView cartImageView1;

    @FXML
    private ImageView cartImageView2;

    @FXML
    private ImageView cartImageView3;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;

    @FXML
    private ProgressBar progressBar3;

    @FXML
    private Label resourceLabel1;

    @FXML
    private Label resourceLabel2;

    @FXML
    private Label resourceLabel3;

    @FXML
    private Button selectedTowerButton;

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
    private Button exitButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resumeButton;


    float progress;
    private Tower selectedTower;
    private Cart selectedCart;
    private ImageView selectedImage;
    private ProgressBar selectedProgressBar;
    private Label selectedResourceLabel;

    private List<Cart> listCartsInRound = new ArrayList<Cart>();
    private List<ImageView> listImageView = new ArrayList<ImageView>();
    private List<ProgressBar> listProgressBar = new ArrayList<ProgressBar>();
    private List<Label> listResourceLabel = new ArrayList<Label>();

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public ChallengingGameController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize() {
        Cart cart1 = new Cart(environmentManager.getCurrentTowerList().get(0).getName(), 20f, 100);
        Cart cart2 = new Cart(environmentManager.getCurrentTowerList().get(1).getName(), 20f, 100);
        Cart cart3 = new Cart(environmentManager.getCurrentTowerList().get(2).getName(), 20f, 100);

        System.out.println(cart1.getTypeResourceCart());
        System.out.println(cart2.getTypeResourceCart());
        System.out.println(cart3.getTypeResourceCart());

        listCartsInRound = List.of(cart1, cart2, cart3);
        listImageView = List.of(cartImageView1, cartImageView2, cartImageView3);
        listProgressBar = List.of(progressBar1, progressBar2, progressBar3);
        listResourceLabel = List.of(resourceLabel1, resourceLabel2, resourceLabel3);
        for (int i = 0; i < listImageView.size(); i++) {
            int finalI = i;
            listImageView.get(finalI).setOnMouseClicked(mouseEvent -> {
                selectedCart = listCartsInRound.get(finalI);
                selectedProgressBar = listProgressBar.get(finalI);
                if (selectedTower != null) {
                    selectedCart.incrementAmountResourceIntoCart(selectedTower);
                    if (selectedCart.getIsIncrementIntoCart()) {
                        progress += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                        selectedProgressBar.setProgress(progress);
                        selectedCart.setIncrementIntoCartToFalse();
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
            listTowerButtons.get(finalI).setOnAction(event -> {
                selectedTowerIndex = finalI;
                listTowerButtons.forEach(button -> {
                    if (button == listTowerButtons.get(finalI)) {
                        selectedTowerButton = button;
                        this.selectedTower = environmentManager.getCurrentTowerList().get(finalI);
                        TranslateTransition translateButton = new TranslateTransition();
                        translateButton.setNode(selectedTowerButton);
                        translateButton.setDuration(Duration.millis((long)selectedTower.getRecoveryTime()));
                        selectedTowerButton.setDisable(true);
                        translateButton.setOnFinished(actionEvent -> {
                            selectedTowerButton.setDisable(false);
                        });
                        translateButton.play();
                        button.setStyle("-fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        TranslateTransition lastMove = new TranslateTransition();
        for (int i = 0; i < listCartsInRound.size(); i++) {
            int finalI = i;
            selectedCart = listCartsInRound.get(finalI);
            selectedImage = listImageView.get(finalI);
            selectedProgressBar = listProgressBar.get(finalI);
            selectedResourceLabel = listResourceLabel.get(finalI);
            lastMove = this.selectedCart.generateAnimation(selectedImage, selectedProgressBar, selectedResourceLabel);
        }

        lastMove.setOnFinished(actionEvent -> {
            System.out.println("End game");
            if (listCartsInRound.get(0).isCartFilledUp() && listCartsInRound.get(1).isCartFilledUp() && listCartsInRound.get(2).isCartFilledUp()) {
                if (environmentManager.getRoundDifficulty().equals("Easy")) {
                    environmentManager.incrementScore(30);
                }
                else if (environmentManager.getRoundDifficulty().equals("Moderate")) {
                    environmentManager.incrementScore(32);
                }
                else if (environmentManager.getRoundDifficulty().equals("Challenging")) {
                    environmentManager.incrementScore(35);
                }
                environmentManager.closeRoundDifficultySelectScreen();
                environmentManager.launchWinnerNextRoundScreen();
            }
            else {
                environmentManager.closeRoundDifficultySelectScreen();
                environmentManager.launchLoserScreen();
            }
        });

        // Implementing a non-blocking delay between starting the cart animations
        ImageView bogus = new ImageView();
        TranslateTransition cartDelayTransition = new TranslateTransition();
        cartDelayTransition.setDuration(Duration.millis(2000));
        cartDelayTransition.setNode(bogus);
        cartDelayTransition.setOnFinished(actionEvent -> {
            listCartsInRound.get(1).startAnimation();
            cartDelayTransition.setOnFinished(actionEvent1 -> {
                listCartsInRound.get(2).startAnimation();
            });
            cartDelayTransition.play();
        });

        listCartsInRound.get(0).startAnimation();
        cartDelayTransition.play();
    }
}
