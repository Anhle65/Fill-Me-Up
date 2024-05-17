package seng201.team0.services;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EnvironmentManager {
    private String playerName;
    private List<Tower> currentTowerList;
    private List<Tower> reservedTowerList;
    private String gameDifficulty;
    private int numberOfRounds;
    private int currentRoundNumber = 1;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private final Consumer<EnvironmentManager> setupScreenLauncher;
    private final Consumer<EnvironmentManager> inventoryScreenLauncher;
    private final Consumer<EnvironmentManager> roundDifficultySelectScreenLauncher;
    private final Consumer<EnvironmentManager> easyGameScreenLauncher;
    private final Consumer<EnvironmentManager> winnerScreenLauncher;
    private final Consumer<EnvironmentManager> loserScreenLauncher;
    private final Consumer<EnvironmentManager> shopScreenLauncher;
    private final Consumer<EnvironmentManager> moderateGameScreenLauncher;
    private final Consumer<EnvironmentManager> challengingGameScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Initialize the default towers on page setup and parse the interface Consumer of all related controllers
     */
    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Consumer<EnvironmentManager> roundDifficultySelectScreenLauncher, Consumer<EnvironmentManager> easyGameScreenLauncher, Consumer<EnvironmentManager> winnerScreenLauncher, Consumer<EnvironmentManager> loserScreenLauncher, Consumer<EnvironmentManager> shopScreenLauncher, Consumer<EnvironmentManager> moderateGameScreenLauncher, Consumer<EnvironmentManager> challengingGameScreenLauncher,Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.roundDifficultySelectScreenLauncher = roundDifficultySelectScreenLauncher;
        this.easyGameScreenLauncher = easyGameScreenLauncher;
        this.winnerScreenLauncher = winnerScreenLauncher;
        this.loserScreenLauncher = loserScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.moderateGameScreenLauncher = moderateGameScreenLauncher;
        this.challengingGameScreenLauncher = challengingGameScreenLauncher;
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
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }
    /**
     * set player name which will be input by user from keyboard
     * @param playerName
     */
    public void setName(String playerName) {
        this.playerName = playerName;
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
        launchRoundDifficultySelectScreen();
    }

    public void closeRoundDifficultySelectScreen() {clearScreen.run();}

    public void returnedSetupScreen() {
        clearScreen.run();
    }

    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    public void launchRoundDifficultySelectScreen() {roundDifficultySelectScreenLauncher.accept(this);}

    public void closeEasyGameScreen() {
        clearScreen.run();
    }

    public void launchWinnerNextRoundScreen() { winnerScreenLauncher.accept(this);}

    public void launchLoserScreen() { loserScreenLauncher.accept(this);}

    public void closeWinnerNextRoundScreen() { clearScreen.run(); }

    public void closeLoserScreen() { clearScreen.run();}

    public void launchShopScreen() { shopScreenLauncher.accept(this);}

    public void launchEasyGameScreen() {easyGameScreenLauncher.accept(this);}

    public void launchModerateGameScreen() {moderateGameScreenLauncher.accept(this);}

    public void launchChallengingGameScreen() {challengingGameScreenLauncher.accept(this);}
}
