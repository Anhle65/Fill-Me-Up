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
    private String gameDifficulty;
    private int numberOfRounds;
    private int currentRoundNumber = 1;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private final Consumer<EnvironmentManager> setupScreenLauncher;
    private final Consumer<EnvironmentManager> inventoryScreenLauncher;
    private final Consumer<EnvironmentManager> roundDifficultyScreenLauncher;
    private final Consumer<EnvironmentManager> roundGameScreenLauncher;
    private final Consumer<EnvironmentManager> winnerScreenLauncher;
    private final Consumer<EnvironmentManager> loserScreenLauncher;
    private final Consumer<EnvironmentManager> shopScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Initialize the default towers on page setup and parse the interface Consumer of all related controllers
     */
    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Consumer<EnvironmentManager> roundDifficultyScreenLauncher, Consumer<EnvironmentManager> roundGameScreenLauncher, Consumer<EnvironmentManager> winnerScreenLauncher, Consumer<EnvironmentManager> loserScreenLauncher, Consumer<EnvironmentManager> shopScreenLauncher,Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.roundDifficultyScreenLauncher = roundDifficultyScreenLauncher;
        this.roundGameScreenLauncher = roundGameScreenLauncher;
        this.winnerScreenLauncher = winnerScreenLauncher;
        this.loserScreenLauncher = loserScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(new Tower("fire",40,20,3000), new Tower("water",40,20,3000),
                new Tower("gold",40,20,3000), new Tower("diamond",40,20,3000), new Tower("coal",40,20,3000)));
        launchSetupScreen();
    }

    /**
     * Set the difficult level which will be chosen by user on setup page and keep through the whole game
     * @param difficulty
     */
    public void setGameDifficulty(String difficulty) {this.gameDifficulty = difficulty;}
    /**
     * Get the difficult level
     * @return difficulty in String
     */
    public String getGameDifficulty(){return this.gameDifficulty;}
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

    /**
     * Get the currentRounds which player is at
     * @return currentRounds
     */
    public int getCurrentRoundNumber(){return this.currentRoundNumber;}


    /**
     * Increment the current round when click to play
     */
    public void incrementCurrentRoundNumber(){
        if (this.currentRoundNumber < numberOfRounds)
            this.currentRoundNumber += 1;
    }

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
    public void closeRoundGameScreen() {clearScreen.run();}

    public void returnedSetupScreen() {
        clearScreen.run();
    }

    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    public void launchRoundDifficultyScreen() {roundDifficultyScreenLauncher.accept(this);}

    public void closeRoundDifficultyScreen() {
        clearScreen.run();
    }

    public void launchWinnerNextRoundScreen() { winnerScreenLauncher.accept(this);}

    public void launchLoserScreen() { loserScreenLauncher.accept(this);}

    public void closeWinnerNextRoundScreen() { clearScreen.run(); }

    public void closeLoserScreen() { clearScreen.run();}

    public void launchShopScreen() { shopScreenLauncher.accept(this);}
}
