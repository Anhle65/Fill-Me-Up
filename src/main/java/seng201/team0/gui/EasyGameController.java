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
    private int selectedTowerIndex = -1;

    @FXML
    private ImageView cartImageView1;

    @FXML
    private ImageView cartImageView2;

    @FXML
    private ImageView cartImageView3;

    @FXML
    private Label resourceLabel1;

    @FXML
    private Label resourceLabel2;

    @FXML
    private Label resourceLabel3;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;
    @FXML
    private ProgressBar progressBar3;

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


    float progress1;
    float progress2;
    float progress3;
    private Tower selectedTower;
    private Cart selectedCart;
    private ImageView selectedImage;
    private ProgressBar selectedProgressBar;
    private Label selectedResourceLabel;
    private int roundDifficultySpeed = 0;


    private List<Cart> listCartsInRound = new ArrayList<Cart>();
    private List<ImageView> listImageView = new ArrayList<ImageView>();
    private List<ProgressBar> listProgressBar = new ArrayList<ProgressBar>();
    private List<Label> listResourceLabel = new ArrayList<Label>();
    private InventoryService inventoryService;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public EasyGameController(EnvironmentManager environmentManager, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.inventoryService = inventoryService;
        List<Tower> currentTowers = inventoryService.getCurrentUsedTowerList();
        for(int i=0; i < currentTowers.size(); ++i){
            Tower tower = currentTowers.get(i);
            if(tower.isInUse()) {
                tower.setInUseState(false);
            }
        }
    }

    public void initialize() {
        if (environmentManager.getRoundDifficulty().equals("Easy")) {roundDifficultySpeed = 80;}
        else if (environmentManager.getRoundDifficulty().equals("Moderate")) {roundDifficultySpeed = 100;}
        else if (environmentManager.getRoundDifficulty().equals("Challenging")) {roundDifficultySpeed = 120;}

        long cartSpeed = roundDifficultySpeed + ((long)environmentManager.getCurrentRoundNumber() * 20);

        Cart cart1 = new Cart(inventoryService.getCurrentUsedTowerList().get(0).getName(), cartSpeed, 100);
        Cart cart2 = new Cart(inventoryService.getCurrentUsedTowerList().get(1).getName(), cartSpeed, 100);
        Cart cart3 = new Cart(inventoryService.getCurrentUsedTowerList().get(2).getName(), cartSpeed, 100);

        System.out.println(cart1.getTypeResourceCart());
        System.out.println(cart2.getTypeResourceCart());
        System.out.println(cart3.getTypeResourceCart());

        listCartsInRound = List.of(cart1, cart2, cart3);
        listImageView = List.of(cartImageView1, cartImageView2, cartImageView3);
        listProgressBar = List.of(progressBar1, progressBar2, progressBar3);
        listResourceLabel = List.of(resourceLabel1, resourceLabel2, resourceLabel3);

        cartImageClickEventInit();

        towerSelectionButtonsInit();

        TranslateTransition lastMove = cartAnimationInit();

        endOfRoundProcessdureInit(lastMove);
        startCartAnimation();
    }

    /**
     * Initialize end of round procedure, use lastMove to check if carts are filled, show result win/lose
     * and increment the score if winning
     * @param lastMove
     */
    private void endOfRoundProcessdureInit(TranslateTransition lastMove) {
        lastMove.setOnFinished(actionEvent -> {
            System.out.println("End game");
            if (listCartsInRound.get(0).isCartFilledUp() && listCartsInRound.get(1).isCartFilledUp() && listCartsInRound.get(2).isCartFilledUp()) {
                if (environmentManager.getRoundDifficulty().equals("Easy")) {
                    environmentManager.incrementScore(10);
                }
                else if (environmentManager.getRoundDifficulty().equals("Moderate")) {
                    environmentManager.incrementScore(15);
                }
                else if (environmentManager.getRoundDifficulty().equals("Challenging")) {
                    environmentManager.incrementScore(20);
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
    }

    /**
     * Initialize cart moving animation and return the lastMove
     * @return lastMove
     */
    private TranslateTransition cartAnimationInit() {
        TranslateTransition lastMove = new TranslateTransition();
        for (int i = 0; i < listCartsInRound.size(); i++) {
            int finalI = i;
            selectedCart = listCartsInRound.get(finalI);
            selectedImage = listImageView.get(finalI);
            selectedProgressBar = listProgressBar.get(finalI);
            selectedResourceLabel = listResourceLabel.get(finalI);
            lastMove = this.selectedCart.generateAnimation(selectedImage, selectedProgressBar, selectedResourceLabel);
        }
        return lastMove;
    }

    /**
     * Implementing selected Tower buttons to get resource stats and enable buttons within set recovery time
     */
    private void towerSelectionButtonsInit() {
        List<Button> listTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        TranslateTransition translateButton1 = new TranslateTransition();
        TranslateTransition translateButton2 = new TranslateTransition();
        TranslateTransition translateButton3 = new TranslateTransition();
        TranslateTransition translateButton4 = new TranslateTransition();
        TranslateTransition translateButton5 = new TranslateTransition();
        List<TranslateTransition> translateButtons = List.of(translateButton1, translateButton2, translateButton3, translateButton4, translateButton5);
        for (int i = 0; i < inventoryService.getCurrentUsedTowerList().size(); i++) {
            int finalI = i;
            listTowerButtons.get(finalI).setText(inventoryService.getCurrentUsedTowerList().get(finalI).getName());
            listTowerButtons.get(finalI).setOnAction(event -> {
                selectedTowerIndex = finalI;
                listTowerButtons.forEach(button -> {
                    if (button == listTowerButtons.get(finalI)) {
                        this.selectedTower = inventoryService.getCurrentUsedTowerList().get(finalI);
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
    }

    /**
     * Sets up the cart image mouse-click event to increment amount of resource
     * in the cart, and the progress bar displayed under the cart image
     */
    private void cartImageClickEventInit() {
        for (int i = 0; i < listImageView.size(); i++) {
            int finalI = i;
            listImageView.get(finalI).setOnMouseClicked(mouseEvent -> {
                selectedCart = listCartsInRound.get(finalI);
                selectedProgressBar = listProgressBar.get(finalI);

                if (selectedTower != null) {
                    selectedCart.incrementAmountResourceIntoCart(selectedTower);
                    if (selectedCart.getIsIncrementIntoCart()) {
                        selectedTower.setInUseState(true);
                        if (finalI == 0) {
                            progress1 += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                            selectedProgressBar.setProgress(progress1);
                        }
                        else if (finalI == 1) {
                            progress2 += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                            selectedProgressBar.setProgress(progress2);
                        } else if (finalI == 2) {
                            progress3 += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                            selectedProgressBar.setProgress(progress3);
                        }
                        selectedCart.setIncrementIntoCartToFalse();

                    }
                    selectedTower = null;
                }
            });
        }
    }

    /**
     * Implementing a non-blocking delay between starting the cart animations
     */
    private void startCartAnimation() {
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
