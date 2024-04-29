package seng201.team0.services;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TowerManager {
    private String name;
    private List<Tower> towerList;
    private final List<Tower> defaultRockets = new ArrayList<>();
    private final Consumer<TowerManager> setupScreenLauncher;
    private final Consumer<TowerManager> mainScreenLauncher;
    private final Runnable clearScreen;

    public TowerManager(Consumer<TowerManager> setupScreenLauncher, Consumer<TowerManager> mainScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;
        defaultRockets.addAll(List.of(new Tower("fire",1,40,20,3,true,false), new Tower("water",1,40,20,3,true,false),
                new Tower("food",1,40,20,3,true,false), new Tower("gold",1,40,20,3,true,false), new Tower("diamond",1,40,20,3,true,false),
                new Tower("coal",1,40,20,3,true,false)));
        launchSetupScreen();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(List<Tower> rocketList) {
        this.towerList = rocketList;
    }

    public List<Tower> getDefaultTowers() {
        return defaultRockets;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchMainScreen();
    }

    public void launchMainScreen() {
        mainScreenLauncher.accept(this);
    }

    public void closeMainScreen() {
        System.exit(0);
    }
}
