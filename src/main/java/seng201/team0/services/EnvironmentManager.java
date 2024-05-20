package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EnvironmentManager {
    private int playerCoins;
    private String playerName;
    private List<Tower> currentTowerList;
    private List<Tower> reservedTowerList = new ArrayList<>();
    private List<UpgradeItems> listUpgradeCardsInInventory = new ArrayList<>();
    private String gameDifficulty;
    private String roundDifficulty;
    private int numberOfRounds;
    private int currentRoundNumber = 1;
    private int score = 0;
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
    private final Consumer<EnvironmentManager> winnerGameScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Initialize the default towers on page setup and parse the interface Consumer of all related controllers
     */
    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Consumer<EnvironmentManager> roundDifficultySelectScreenLauncher, Consumer<EnvironmentManager> easyGameScreenLauncher, Consumer<EnvironmentManager> winnerScreenLauncher, Consumer<EnvironmentManager> loserScreenLauncher, Consumer<EnvironmentManager> shopScreenLauncher, Consumer<EnvironmentManager> moderateGameScreenLauncher, Consumer<EnvironmentManager> challengingGameScreenLauncher, Consumer<EnvironmentManager> winnerGameScreenLauncher, Runnable clearScreen) {
//        this.playerCoins = 150;
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.roundDifficultySelectScreenLauncher = roundDifficultySelectScreenLauncher;
        this.easyGameScreenLauncher = easyGameScreenLauncher;
        this.winnerScreenLauncher = winnerScreenLauncher;
        this.loserScreenLauncher = loserScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.moderateGameScreenLauncher = moderateGameScreenLauncher;
        this.challengingGameScreenLauncher = challengingGameScreenLauncher;
        this.winnerGameScreenLauncher = winnerGameScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(
                new Tower("Fire",40,20,3000),
                new Tower("Water",40,20,3000),
                new Tower("Gold",40,20,3000),
                new Tower("Diamond",40,20,3000),
                new Tower("Coal",40,20,3000))
        );
        launchSetupScreen();
    }

    /**
     * Get the player coin
     * @return playerCoins
     */
//    public int getPlayerCoins(){return this.playerCoins;}

    public List<UpgradeItems> getListUpgradeCardsInInventory(){return this.listUpgradeCardsInInventory;}
    /**
     * Get list of the reserved tower
     * @return reservedTowerList
     */
    public List<Tower> getReservedTowerList(){return this.reservedTowerList;}

    /**
     * Get the current score
     * @return score
     */
    public int getScore() {return this.score;}

    /**
     * Increment the score by the given amount
     * @param amount amount to add to the score
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }

    /**
     * Clear player score and reset score to zero when start a new game
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Get current round difficulty
     */
    public String getRoundDifficulty() {return this.roundDifficulty;}

    /**
     * Set the round difficulty level inside the chosen game difficulty
     * @param roundDifficulty
     */
    public void setRoundDifficulty(String roundDifficulty) {this.roundDifficulty = roundDifficulty;}

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

    public void closeWinnerGameScreen() { clearScreen.run();}

    public void closeShopScreen() { clearScreen.run();}

    public void launchShopScreen() { shopScreenLauncher.accept(this);}

    public void launchEasyGameScreen() {easyGameScreenLauncher.accept(this);}

    public void launchModerateGameScreen() {moderateGameScreenLauncher.accept(this);}

    public void launchChallengingGameScreen() {challengingGameScreenLauncher.accept(this);}

    public void launchWinnerGameScreen() {winnerGameScreenLauncher.accept(this);}
}
