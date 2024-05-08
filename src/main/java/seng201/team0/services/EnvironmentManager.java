package seng201.team0.services;

import seng201.team0.gui.DifficultyController;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EnvironmentManager {
    private String userName;
    private List<Tower> currentTowerList;
    private List<Tower> reservedTowerList;
    private String difficulty;
    private int numberOfRounds;
    private int currentRoundNumber = 5;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private final Consumer<EnvironmentManager> setupScreenLauncher;
    private final Consumer<EnvironmentManager> inventoryScreenLauncher;
    private final Consumer<EnvironmentManager> roundDifficultyScreenLauncher;
    private final Consumer<EnvironmentManager> roundGameScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Initialize the default towers on page setup and parse the interface Consumer of all related controllers
     */
    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Consumer<EnvironmentManager> roundDifficultyScreenLauncher, Consumer<EnvironmentManager> roundGameScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.roundDifficultyScreenLauncher = roundDifficultyScreenLauncher;
        this.roundGameScreenLauncher = roundGameScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(new Tower("fire",40,20,3), new Tower("water",40,20,3),
                new Tower("food",40,20,3), new Tower("gold",40,20,3), new Tower("diamond",40,20,3),
                new Tower("coal",40,20,3)));
        System.out.println(defaultTowers.get(0).getType());
        launchSetupScreen();
    }

    /**
     * Set the difficult level which will be chosen by user on setup page and keep through the whole game
     * @param difficulty
     */
    public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
    /**
     * Get the difficult level
     * @return difficulty in String
     */
    public String getDifficulty(){return this.difficulty;}
    /**
     * set the number of rounds which will be chosen by user on setup page and keep through the whole game
     * @param numberOfRounds
     */
    public void setNumberOfRounds(int numberOfRounds){this.numberOfRounds = numberOfRounds;}
    /**
     * Get numberOfRounds
     * @return numberOfRounds in int
     */
    public int getNumberOfRounds(){return this.numberOfRounds;}

    public int getCurrentRoundNumber(){return this.currentRoundNumber;}

    public void setCurrentRoundNumber(int currentRoundNumber){this.currentRoundNumber = currentRoundNumber;}
    /**
     * Get the player name
     * @return player name in String
     */
    public String getName() {
        return userName;
    }
    /**
     * set player name which will be input by user from keyboard
     * @param playerName
     */
    public void setName(String playerName) {
        this.userName = playerName;
    }
    /**
     * @return List of the current towers are used
     */
    public List<Tower> getCurrentTowerList() {
        return currentTowerList;
    }
    /**
     * set the current towers are used
     * @param currentTowerList
     */
    public void setCurrentTowerList(List<Tower> currentTowerList) {
        this.currentTowerList = currentTowerList;
    }
    /**
     * @return List of the default towers which can be selected by player on the setup page
     */
    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchRoundDifficultyScreen();
    }
    public void launchRoundGameScreen() {
        roundGameScreenLauncher.accept(this);
    }

    public void returnedSetupScreen() {
        clearScreen.run();
    }

    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    public void launchRoundDifficultyScreen() {
        roundDifficultyScreenLauncher.accept(this);

    }

    public void closeRoundDifficultyScreen() {
        clearScreen.run();
    }
}
