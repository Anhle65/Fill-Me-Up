package seng201.team0.services;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EnvironmentManager {
    private String type;
    private List<Tower> usedtowerList;
    private List<Tower> reservedTowerList;
    private String difficulty;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private final Consumer<EnvironmentManager> setupScreenLauncher;
    private final Consumer<EnvironmentManager> inventoryScreenLauncher;
    private final Runnable clearScreen;

    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(new Tower("fire",1,40,20,3), new Tower("water",1,40,20,3),
                new Tower("food",1,40,20,3), new Tower("gold",1,40,20,3), new Tower("diamond",1,40,20,3),
                new Tower("coal",1,40,20,3)));
        System.out.println(defaultTowers.get(0).getType());
        launchSetupScreen();
    }
    public void setDifficulty(String diff) {this.difficulty = diff;}
    public String getDifficulty(){return this.difficulty;}
    public String getName() {
        return type;
    }

    public void setName(String type) {
        this.type = type;
    }

    public List<Tower> getTowerList() {
        return usedtowerList;
    }

    public void setTowerList(List<Tower> usedtowerList) {
        this.usedtowerList = usedtowerList;
    }

    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }

    public void returnedSetupScreen() {
        clearScreen.run();
        launchSetupScreen();
    }

    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    public void closeInventoryScreen() {
        System.exit(0);
    }
}
