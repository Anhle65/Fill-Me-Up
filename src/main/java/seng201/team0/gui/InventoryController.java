package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItem;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    @FXML
    private Label playerName;
    @FXML
    private Label tower1Time;
    @FXML
    private Label tower2Time;
    @FXML
    private Label tower3Time;
    @FXML
    private Label tower4Time;
    @FXML
    private Label tower5Time;
    @FXML
    private Label tower1Resource;
    @FXML
    private Label tower2Resource;
    @FXML
    private Label tower3Resource;
    @FXML
    private Label tower4Resource;
    @FXML
    private Label tower5Resource;
    @FXML
    private Label tower1Level;
    @FXML
    private Label tower2Level;
    @FXML
    private Label tower3Level;
    @FXML
    private Label tower4Level;
    @FXML
    private Label tower5Level;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    @FXML
    private Button tower4;
    @FXML
    private Button tower5;
    @FXML
    private Button reservedTower1;
    @FXML
    private Button reservedTower2;
    @FXML
    private Button reservedTower3;
    @FXML
    private Button reservedTower4;
    @FXML
    private Button reservedTower5;
    @FXML
    private Button upgradeCard1;
    @FXML
    private Button upgradeCard2;
    @FXML
    private Button upgradeCard3;
    @FXML
    private Button upgradeCard4;
    @FXML
    private Button upgradeCard5;
    private int selectedCurrentTowerIndex = -1;
    private int selectedUpgradeCardIndex = -1;
    private int selectedReversedTowerIndex = -1;
    private Tower selectedCurrentTowers = null;
    private Button selectedCurrentTowersButton;
    private Tower selectedReservedTowers = null;
    private Button selectedReservedTowersButton;
    private UpgradeItems selectedUpgradeCard = null;
    private Button selectedUpgradeCardButton;
    private EnvironmentManager environmentManager;
    private ShopService shopService;
    private InventoryService inventoryService;
    private List<Label> towerTimeList;
    private List<Label> towerResourceList;
    private List<Label> towerLevelList;
    private List<Button> towerButtons = new ArrayList<>();
    private List<Button> reservedTowerButton = new ArrayList<>();
    private List<Button> upgradeCardButton = new ArrayList<>();

    public InventoryController(EnvironmentManager environmentManager, ShopService shopService, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.shopService = shopService;
        this.inventoryService = inventoryService;
    }

    @FXML
    public void initialize() {
        this.playerName.setText(this.environmentManager.getPlayerName());
        this.towerTimeList = List.of(this.tower1Time, this.tower2Time, this.tower3Time, this.tower4Time, this.tower5Time);
        this.towerResourceList = List.of(this.tower1Resource, this.tower2Resource, this.tower3Resource, this.tower4Resource, this.tower5Resource);
        this.towerLevelList = List.of(this.tower1Level, this.tower2Level, this.tower3Level, this.tower4Level, this.tower5Level);
        this.towerButtons = List.of(this.tower1, this.tower2, this.tower3, this.tower4, this.tower5);
        this.reservedTowerButton = List.of(this.reservedTower1, this.reservedTower2, this.reservedTower3, this.reservedTower4, this.reservedTower5);
        this.upgradeCardButton = List.of(this.upgradeCard1, this.upgradeCard2, this.upgradeCard3, this.upgradeCard4, this.upgradeCard5);

        for(int i = 0; i < towerButtons.size(); ++i) {
            int finalI = i;
            if(finalI < inventoryService.getCurrentTowerList().size()) {
                this.updateStats((Tower) this.inventoryService.getCurrentTowerList().get(finalI), (Label) this.towerTimeList.get(finalI), (Label) this.towerResourceList.get(finalI), (Label) this.towerLevelList.get(finalI));
                ((Button) towerButtons.get(finalI)).setText(String.valueOf(((Tower) this.inventoryService.getCurrentTowerList().get(finalI)).getName()));
            }
            ((Button)towerButtons.get(finalI)).setOnAction((event) -> {
//                this.selectedCurrentTowerIndex = finalI;
                towerButtons.forEach((button) -> {
                    if (button == towerButtons.get(finalI)) {
                        this.selectedCurrentTowerIndex = finalI;
                        this.selectedCurrentTowersButton = button;
                        if(finalI < inventoryService.getCurrentTowerList().size()) {
                            this.selectedCurrentTowers = (Tower) this.inventoryService.getCurrentTowerList().get(finalI);
                            System.out.println("Selected current tower: " + selectedCurrentTowers);
                        }
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        for(int j = 0; j < reservedTowerButton.size(); ++j){
            int finalJ = j;
            if(finalJ < inventoryService.getReservedTowerList().size())
                reservedTowerButton.get(finalJ).setText(inventoryService.getReservedTowerList().get(finalJ).getName());
            reservedTowerButton.get(finalJ).setOnAction((event) -> {
//                this.selectedReversedTowerIndex = finalJ;
                reservedTowerButton.forEach((button) -> {
                    if (button == reservedTowerButton.get(finalJ)) {
                        this.selectedReversedTowerIndex = finalJ;
                        this.selectedUpgradeCardIndex = -1;
                        if(this.selectedUpgradeCardButton != null)
                            this.selectedUpgradeCardButton.setStyle("");
                        this.selectedReservedTowersButton = button;
                        if(finalJ < inventoryService.getReservedTowerList().size())
                            selectedReservedTowers = inventoryService.getReservedTowerList().get(finalJ);
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        for(int k = 0; k < upgradeCardButton.size(); ++k){
            int finalK = k;
            if(finalK < inventoryService.getListUpgradeCardsInInventory().size())
                upgradeCardButton.get(finalK).setText(inventoryService.getListUpgradeCardsInInventory().get(finalK).getName());
            upgradeCardButton.get(finalK).setOnAction((event) -> {
//                this.selectedUpgradeCardIndex = finalK;
                upgradeCardButton.forEach((button) -> {
                    if (button == upgradeCardButton.get(finalK)) {
                        this.selectedUpgradeCardIndex = finalK;
                        this.selectedReversedTowerIndex = -1;
                        if(selectedReservedTowersButton != null)
                            selectedReservedTowersButton.setStyle("");
                        this.selectedUpgradeCardButton = button;
                        if(finalK < inventoryService.getListUpgradeCardsInInventory().size())
                            selectedUpgradeCard = inventoryService.getListUpgradeCardsInInventory().get(finalK);
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

    private void showAllCurrentTower(){
        for(int i = 0; i < towerButtons.size(); ++i) {
            int finalI = i;
            if(finalI < inventoryService.getCurrentTowerList().size()) {
                this.updateStats((Tower) this.inventoryService.getCurrentTowerList().get(finalI), (Label) this.towerTimeList.get(finalI), (Label) this.towerResourceList.get(finalI), (Label) this.towerLevelList.get(finalI));
                ((Button) towerButtons.get(finalI)).setText(String.valueOf(((Tower) this.inventoryService.getCurrentTowerList().get(finalI)).getName()));
            }
            ((Button)towerButtons.get(finalI)).setOnAction((event) -> {
//                this.selectedCurrentTowerIndex = finalI;
                towerButtons.forEach((button) -> {
                    if (button == towerButtons.get(finalI)) {
                        this.selectedCurrentTowerIndex = finalI;
                        this.selectedCurrentTowersButton = towerButtons.get(finalI);
                        if(finalI < inventoryService.getCurrentTowerList().size()) {
                            this.selectedCurrentTowers = (Tower) this.inventoryService.getCurrentTowerList().get(finalI);
                            System.out.println("Selected current tower: " + selectedCurrentTowers.getName());
                        }
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }
    private void showObjectInButton(List<Button> buttons, List<Tower> towerList, List<UpgradeItems> itemsList){
        if(!(towerList == null && itemsList == null)) {
            for (int j = 0; j < buttons.size(); ++j) {
                int finalJ = j;
                if (itemsList == null) {
                    if (finalJ < towerList.size())
                        buttons.get(finalJ).setText(towerList.get(finalJ).getName());
                    else
                        buttons.get(finalJ).setText("Empty");
                } else if (towerList == null) {
                    if (finalJ < itemsList.size())
                        buttons.get(finalJ).setText(itemsList.get(finalJ).getName());
                    else
                        buttons.get(finalJ).setText("Empty");
                }
            }
        }
    }
    private void updateStats(Tower tower, Label towerTime, Label towerResource, Label towerLevel) {
        towerTime.setText(String.valueOf(tower.getRecoveryTime()));
        towerResource.setText(String.valueOf(tower.getResourceAmount()));
        towerLevel.setText(String.valueOf(tower.getLevel()));
    }

    @FXML
    private void onSwappedClicked() {
        System.out.println("onSwappedClicked");
        if (selectedCurrentTowers != null && selectedReservedTowers != null){
            Tower tower = inventoryService.getCurrentTowerList().get(selectedCurrentTowerIndex);
            inventoryService.getCurrentTowerList().set(selectedCurrentTowerIndex, selectedReservedTowers);
//            environmentManager.getReservedTowerList().remove(selectedReservedTowers);
            inventoryService.getReservedTowerList().set(selectedReversedTowerIndex, selectedCurrentTowers);
        }else if(selectedCurrentTowerIndex != -1 && selectedReservedTowers != null){
            System.out.println("Do something...");
            inventoryService.getCurrentTowerList().add(selectedReservedTowers);
            inventoryService.getReservedTowerList().remove(selectedReservedTowers);
        }else if(selectedCurrentTowers != null && selectedReservedTowers == null){
            System.out.println("Please choose 1 reserved tower to swap");
            inventoryService.getReservedTowerList().set(selectedReversedTowerIndex, selectedCurrentTowers);
        }else
            System.out.println("Please choose 2 buttons to do the action");
        this.showObjectInButton(reservedTowerButton, inventoryService.getReservedTowerList(), null);
        selectedCurrentTowers = null;
        selectedCurrentTowerIndex = -1;
        selectedReversedTowerIndex = -1;
        selectedReservedTowers = null;
        selectedReservedTowersButton.setStyle("");
        selectedCurrentTowersButton.setStyle("");
    }

    @FXML
    private void onUpgradedClicked() {
        System.out.println("onUpgradedClicked");
        if(selectedUpgradeCard != null && selectedCurrentTowers != null){
            System.out.println("selectedCurrentTowers1st: " + selectedCurrentTowers);
            inventoryService.upgradeTower(selectedUpgradeCard, selectedCurrentTowers);
            System.out.println("selectedCurrentTowers2nd: " + selectedCurrentTowers);
//            System.out.println("Update: " + selectedCurrentTowers.getName());
            System.out.println("selectedCurrentTowersIndex1st " + selectedCurrentTowerIndex);
            this.updateStats((Tower) selectedCurrentTowers, (Label) this.towerTimeList.get(selectedCurrentTowerIndex), (Label) this.towerResourceList.get(selectedCurrentTowerIndex), (Label) this.towerLevelList.get(selectedCurrentTowerIndex));
            System.out.println("selectedCurrentTowersIndex2nd " + selectedCurrentTowerIndex);
            selectedCurrentTowersButton.setText(String.valueOf(selectedCurrentTowers.getName()));
            selectedUpgradeCardButton.setStyle("");
            selectedCurrentTowersButton.setStyle("");
            inventoryService.getListUpgradeCardsInInventory().remove(selectedUpgradeCard);
            this.showObjectInButton(this.upgradeCardButton,null, inventoryService.getListUpgradeCardsInInventory());
        }else if (selectedUpgradeCard != null) {
            System.out.println("Please choose the current tower to upgrade");  //Care when decrement time or not???
        }else if (selectedCurrentTowers != null) {
            System.out.println("Please choose the card to upgrade for tower");
        }else
            System.out.println("Please choose 1 card and 1 tower to do the action");
        this.showAllCurrentTower();
        selectedCurrentTowers = null;
        selectedUpgradeCard = null;
    }

    @FXML
    private void onReturnedClicked(){
        environmentManager.returnedSetupScreen();
//        environmentManager.launchRoundDifficultySelectScreen();
        environmentManager.launchShopScreen();
    }
}