package seng201.team0.gui;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;


import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import seng201.team0.models.RoundManager;
import seng201.team0.services.EnvironmentManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class RoundController {
    private EnvironmentManager environmentManager;
    private RoundManager roundManager;
    private int trackDistance;
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
    private Label tower1Label;

    @FXML
    private Label tower2Label;

    @FXML
    private Label tower3Label;

    @FXML
    private Label tower4Label;

    @FXML
    private Label tower5Label;



    public RoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }


    public void initialize() {
//        this.roundManager = new RoundManager(this.environmentManager);
//        this.trackDistance = roundManager.getTrackDistance();

        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(cartImageView);
        translate1.setDuration(Duration.millis(3000));
        translate1.setByX(800);


        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(cartImageView);
        translate2.setDuration(Duration.millis(1000));
        translate2.setByY(100);


        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(cartImageView);
        translate3.setDuration(Duration.millis(3000));
        translate3.setByX(-800);

        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cartImageView);
        translate4.setDuration(Duration.millis(1000));
        translate4.setByY(100);

        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(cartImageView);
        translate5.setDuration(Duration.millis(3000));
        translate5.setByX(800);


        translate1.setOnFinished(actionEvent -> translate2.play());
        translate2.setOnFinished(actionEvent -> translate3.play());
        translate3.setOnFinished(actionEvent -> translate4.play());
        translate4.setOnFinished(actionEvent -> translate5.play());

        translate1.play();

    }
    public void shootInCartImageViewMouseClicked(MouseEvent mouseEvent) {
        if (tower1Selected) {
            for (int i=5; i>0; i--) {
                tower1Label.setText(String.valueOf(i));
            }
        }
//        if (tower2Selected) {
//            for (int i=5; i>0; i--) {
//                tower2Label.setText(String.valueOf(i));
//            }
//        }
//        if (tower3Selected) {
//            for (int i=5; i>0; i--) {
//                tower3Label.setText(String.valueOf(i));
//            }
//        }
//        if (tower4Selected) {
//            for (int i=5; i>0; i--) {
//                tower4Label.setText(String.valueOf(i));
//            }
//        }
//        if (tower5Selected) {
//            for (int i=5; i>0; i--) {
//                tower5Label.setText(String.valueOf(i));
//            }
//        }

    }

    public void onTower1ButtonClicked(ActionEvent actionEvent) {
        tower1Selected = true;
        for (int i=4; i>=0; i--) {
            tower1Label.setText(String.valueOf(i));
        }
    }

//    public void onTower2ButtonClicked(ActionEvent actionEvent) {
//        tower2Selected = true;
//    }
//    public void onTower3ButtonClicked(ActionEvent actionEvent) {
//        tower3Selected = true;
//    }
//
//    public void onTower4ButtonClicked(ActionEvent actionEvent) {
//        tower4Selected = true;
//    }
//
//    public void onTower5ButtonClicked(ActionEvent actionEvent) {
//        tower5Selected = true;
//    }




}