package seng201.team0.gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import seng201.team0.services.EnvironmentManager;
import javafx.scene.image.ImageView;


public class ModerateRoundController {

    private EnvironmentManager environmentManager;

    @FXML
    private ImageView cartImageView1;

    @FXML
    private ImageView cartImageView2;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;

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
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public ModerateRoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    public void initialize() {


        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(cartImageView1);
        translate1.setDuration(Duration.millis(2000));
        translate1.setByX(700);

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(cartImageView2);
        translate2.setDuration(Duration.millis(1000));
        translate2.setByY(100);


        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(cartImageView1);
        translate3.setDuration(Duration.millis(2000));
        translate3.setByX(-700);

        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cartImageView1);
        translate4.setDuration(Duration.millis(1000));
        translate4.setByY(100);

        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(cartImageView2);
        translate5.setDuration(Duration.millis(5000));
        translate5.setByX(700);

        TranslateTransition translatebar1 = new TranslateTransition();
        translatebar1.setNode(progressBar1);
        translatebar1.setDuration(Duration.millis(3000));
        translatebar1.setByX(700);

        TranslateTransition translatebar2 = new TranslateTransition();
        translatebar2.setNode(progressBar2);
        translatebar2.setDuration(Duration.millis(5000));
        translatebar2.setByY(100);

        TranslateTransition translatebar3 = new TranslateTransition();
        translatebar3.setNode(progressBar1);
        translatebar3.setDuration(Duration.millis(3000));
        translatebar3.setByX(-700);

        TranslateTransition translatebar4 = new TranslateTransition();
        translatebar4.setNode(progressBar1);
        translatebar4.setDuration(Duration.millis(1000));
        translatebar4.setByY(100);

        TranslateTransition translatebar5 = new TranslateTransition();
        translatebar5.setNode(progressBar2);
        translatebar5.setDuration(Duration.millis(2000));
        translatebar5.setByX(700);


        translate1.setOnFinished(actionEvent -> translate2.play());
        translate2.setOnFinished(actionEvent -> translate3.play());
        translate3.setOnFinished(actionEvent -> translate4.play());
        translate4.setOnFinished(actionEvent -> translate5.play());

        translatebar1.setOnFinished(actionEvent -> translatebar2.play());
        translatebar2.setOnFinished(actionEvent -> translatebar3.play());
        translatebar3.setOnFinished(actionEvent -> translatebar4.play());
        translatebar4.setOnFinished(actionEvent -> translatebar5.play());

        translate1.play();
        translatebar1.play();

    }
}
