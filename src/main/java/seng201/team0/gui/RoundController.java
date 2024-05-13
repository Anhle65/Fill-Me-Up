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

import java.util.List;

import javafx.scene.control.Button;


public class RoundController{
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
    @FXML float progress;
    private Tower selectedTower;

    private List<Cart> listCartsInRound;
//    private List<Button> listCartsButton;
    private Cart selectedCart;


    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public RoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }


    public void initialize() {
//        this.roundManager = new RoundManager(this.environmentManager);
//        this.trackDistance = roundManager.getTrackDistance();
        listCartsInRound = List.of(new Cart("water", 20f, 100), new Cart("fire", 20f, 120));
        List<ImageView> listImageView = List.of(cartImageView);
        for(int i = 0; i < listImageView.size(); i++) {
            int finalI = i;
            listImageView.get(finalI).setOnMouseClicked(mouseEvent -> {
                selectedCart = listCartsInRound.get(finalI);
                if (selectedTower != null){
                    selectedCart.incrementAmountResourceIntoCart(selectedTower);
                    if (selectedCart.getIsIncrementIntoCart()) {
                        progress += (float) selectedTower.getResourceAmount() / selectedCart.getSizeOfCart();
                        progressCart1.setProgress(progress);
                        selectedCart.setIncrementIntoCartToFalse();
                    }
                    System.out.println("Mouse event " + selectedCart.getTypeResourceCart() + " " + selectedCart.getCurrentAmountOfCart());
                }
            });
        }
        List<Button> listTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);

//        for (int j = 0; j < listCartsInRound.size(); j++){
//            int finalJ = j;
//            listCartsButton.get(finalJ).setText(listCartsInRound.get(finalJ).getTypeResourceCart());
//            listCartsButton.get(finalJ).setOnAction(event -> {
//                listCartsButton.forEach(bt -> {
//                    if (bt == listCartsButton.get(finalJ)) {
//                        this.selectedCart = listCartsInRound.get(finalJ);
//                    listCartsInRound.get(finalI).incrementAmountResourceIntoCart(selectedTower);
//                    System.out.println(listCartsInRound.get(finalI).getCurrentAmountOfCart());
//                        bt.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
//                    } else {
//                        bt.setStyle("");
//                    }
//                });
//            });
//        }

        for (int i = 0; i < environmentManager.getCurrentTowerList().size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            listTowerButtons.get(finalI).setText(environmentManager.getCurrentTowerList().get(finalI).getType());
            listTowerButtons.get(i).setOnAction(event -> {
                selectedTowerIndex = finalI;
                listTowerButtons.forEach(button -> {
                    if (button == listTowerButtons.get(finalI)) {
                        selectedTowerButton = button;
                        this.selectedTower = environmentManager.getCurrentTowerList().get(finalI);
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

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


        translate1.setOnFinished(actionEvent -> translate2.play());
        translate2.setOnFinished(actionEvent -> translate3.play());
        translate3.setOnFinished(actionEvent -> translate4.play());
        translate4.setOnFinished(actionEvent -> translate5.play());

        translate1.play();

    }

}


