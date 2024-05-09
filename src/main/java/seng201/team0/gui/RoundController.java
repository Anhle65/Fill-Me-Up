package seng201.team0.gui;


import javafx.fxml.FXML;


import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import seng201.team0.models.RoundManager;
import seng201.team0.services.EnvironmentManager;

public class RoundController {
    private EnvironmentManager environmentManager;
    private RoundManager roundManager;
    private int trackDistance;

    @FXML
    private ImageView cartImageView;

    public RoundController(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }


    public void initialize() {
        this.roundManager = new RoundManager(this.environmentManager);
        this.trackDistance = roundManager.getTrackDistance();
    }



}