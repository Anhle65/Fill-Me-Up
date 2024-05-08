package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import seng201.team0.models.RoundManager;
import seng201.team0.services.EnvironmentManager;

public class DifficultyController {
    private String easy;
    private String moderate;
    private String challenging;
    private String currentDifficulty;
    private int roundNumbers;

    @FXML
    private RadioButton easyRadioButton;

    @FXML
    private RadioButton moderateRadioButton;

    @FXML
    private RadioButton challengingRadioButton;

    @FXML
    private Button letPlayButton;


    private void onLetPlayclicked(){
        }
    }
    /**
     * Sets up various game variables according to the current round counter and difficulty
     * on a given RoundManager object.
     * @param rm RoundManager object for game environment changes to be made on
     */
//    public void roundDifficultySet(RoundManager rm) {
//
//        if (currentDifficulty == "easy") {
//            // Set variables for easy mode
//            rm.setTrackDistance(10);
//        } else if (currentDifficulty == "moderate") {
//            // Set variables for moderate mode
//            rm.setTrackDistance(8);
//        } else if (currentDifficulty == "challenging") {
//            // Set variables for challenging mode
//            rm.setTrackDistance(5);
//        }
//    }
//

