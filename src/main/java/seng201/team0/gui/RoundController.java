package seng201.team0.gui;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;


import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.models.Cart;
import seng201.team0.models.RoundManager;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;


public class RoundController {
    private EnvironmentManager environmentManager;
    private RoundManager roundManager;
    private int trackDistance;
    private int selectedTowerIndex = -1;
    private boolean tower1Selected = false;
    private boolean tower2Selected = false;
    private boolean tower3Selected = false;
    private boolean tower4Selected = false;
    private boolean tower5Selected = false;

    @FXML
    private ImageView cartImageView;

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

    @FXML
    private Button selectedTowerButton;

    @FXML
    private ProgressBar progressCart1;

    @FXML
    float progress;
    private Tower selectedTower;

    private List<Cart> listCartsInRound;
    //    private List<Button> listCartsButton;
    private Cart selectedCart;
    private boolean isFull = false;
    private List<ImageView> view = new ArrayList<>();

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public RoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void isReachDestination(){
        if ((cartImageView.getX() == 700) && (cartImageView.getY() == 200)) {
            System.out.println("reach");}
    }

    public void initialize() {
//        this.roundManager = new RoundManager(this.environmentManager);
//        this.trackDistance = roundManager.getTrackDistance();
        Cart cart = new Cart(environmentManager.getCurrentTowerList().get(0).getType(), 20f, 100);
        System.out.println(cart.getTypeResourceCart());
        listCartsInRound = List.of(cart, new Cart("fire", 20f, 120));
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
        if ((cartImageView.getX() == 700) && (cartImageView.getY() == 200)) {
            System.out.println("reach");}


        List<Button> listTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);

        for (int i = 0; i < environmentManager.getCurrentTowerList().size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            listTowerButtons.get(finalI).setText(environmentManager.getCurrentTowerList().get(finalI).getType());
            listTowerButtons.get(i).setOnAction(event -> {
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

        // TODO: turn building animations into a RoundManager method. This will save having to explicitly type out each and every animation sequence.

//        List<>
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(cartImageView);
        translate1.setDuration(Duration.millis(3000));
        translate1.setByX(700);

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(cartImageView);
        translate2.setDuration(Duration.millis(1000));
        translate2.setByY(100);


        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(cartImageView);
        translate3.setDuration(Duration.millis(3000));
        translate3.setByX(-700);

        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cartImageView);
        translate4.setDuration(Duration.millis(1000));
        translate4.setByY(100);

        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(cartImageView);
        translate5.setDuration(Duration.millis(3000));
        translate5.setByX(700);

        TranslateTransition translatebar1 = new TranslateTransition();
        translatebar1.setNode(progressCart1);
        translatebar1.setDuration(Duration.millis(3000));
        translatebar1.setByX(700);

        TranslateTransition translatebar2 = new TranslateTransition();
        translatebar2.setNode(progressCart1);
        translatebar2.setDuration(Duration.millis(1000));
        translatebar2.setByY(100);

        TranslateTransition translatebar3 = new TranslateTransition();
        translatebar3.setNode(progressCart1);
        translatebar3.setDuration(Duration.millis(3000));
        translatebar3.setByX(-700);

        TranslateTransition translatebar4 = new TranslateTransition();
        translatebar4.setNode(progressCart1);
        translatebar4.setDuration(Duration.millis(1000));
        translatebar4.setByY(100);

        TranslateTransition translatebar5 = new TranslateTransition();
        translatebar5.setNode(progressCart1);
        translatebar5.setDuration(Duration.millis(3000));
        translatebar5.setByX(700);


        translate1.setOnFinished(actionEvent -> translate2.play());
        translate2.setOnFinished(actionEvent -> translate3.play());
        translate3.setOnFinished(actionEvent -> translate4.play());
        translate4.setOnFinished(actionEvent -> translate5.play());

        translatebar1.setOnFinished(actionEvent -> translatebar2.play());
        translatebar2.setOnFinished(actionEvent -> translatebar3.play());
        translatebar3.setOnFinished(actionEvent -> translatebar4.play());
        translatebar4.setOnFinished(actionEvent -> translatebar5.play());

        translatebar5.setOnFinished(actionEvent -> {
            if (isFull) {
                environmentManager.closeRoundGameScreen();
                environmentManager.launchWinnerNextRoundScreen();
            }
            else {
                environmentManager.closeRoundGameScreen();
                environmentManager.launchLoserScreen();
            }
        });

        translate1.play();
        translatebar1.play();

        }
    }





