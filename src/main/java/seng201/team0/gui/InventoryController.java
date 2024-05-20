package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItem;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;
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
    private List<Label> towerTimeList;
    private List<Label> towerResourceList;
    private List<Label> towerLevelList;
    private List<Button> towerButtons = new ArrayList<>();
    private List<Button> reservedTowerButton = new ArrayList<>();
    private List<Button> upgradeCardButton = new ArrayList<>();

    public InventoryController(EnvironmentManager environmentManager, ShopService shopService) {
        this.environmentManager = environmentManager;
        this.shopService = shopService;
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
            if(finalI < environmentManager.getCurrentTowerList().size()) {
                this.updateStats((Tower) this.environmentManager.getCurrentTowerList().get(finalI), (Label) this.towerTimeList.get(finalI), (Label) this.towerResourceList.get(finalI), (Label) this.towerLevelList.get(finalI));
                ((Button) towerButtons.get(finalI)).setText(String.valueOf(((Tower) this.environmentManager.getCurrentTowerList().get(finalI)).getName()));
            }
            ((Button)towerButtons.get(finalI)).setOnAction((event) -> {
//                this.selectedCurrentTowerIndex = finalI;
                towerButtons.forEach((button) -> {
                    if (button == towerButtons.get(finalI)) {
                        this.selectedCurrentTowerIndex = finalI;
                        this.selectedCurrentTowersButton = button;
                        if(finalI < environmentManager.getCurrentTowerList().size()) {
                            this.selectedCurrentTowers = (Tower) this.environmentManager.getCurrentTowerList().get(finalI);
                            System.out.println("Selected current tower: " + selectedCurrentTowers.getName());
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
            if(finalJ < environmentManager.getReservedTowerList().size())
                reservedTowerButton.get(finalJ).setText(environmentManager.getReservedTowerList().get(finalJ).getName());
            reservedTowerButton.get(finalJ).setOnAction((event) -> {
//                this.selectedReversedTowerIndex = finalJ;
                reservedTowerButton.forEach((button) -> {
                    if (button == reservedTowerButton.get(finalJ)) {
                        this.selectedReversedTowerIndex = finalJ;
                        this.selectedUpgradeCardIndex = -1;
                        if(this.selectedUpgradeCardButton != null)
                            this.selectedUpgradeCardButton.setStyle("");
                        this.selectedReservedTowersButton = button;
                        if(finalJ < environmentManager.getReservedTowerList().size())
                            selectedReservedTowers = environmentManager.getReservedTowerList().get(finalJ);
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        for(int k = 0; k < upgradeCardButton.size(); ++k){
            int finalK = k;
            if(finalK < environmentManager.getListUpgradeCardsInInventory().size())
                upgradeCardButton.get(finalK).setText(environmentManager.getListUpgradeCardsInInventory().get(finalK).getName());
            upgradeCardButton.get(finalK).setOnAction((event) -> {
//                this.selectedUpgradeCardIndex = finalK;
                upgradeCardButton.forEach((button) -> {
                    if (button == upgradeCardButton.get(finalK)) {
                        this.selectedUpgradeCardIndex = finalK;
                        this.selectedReversedTowerIndex = -1;
                        if(selectedReservedTowersButton != null)
                            selectedReservedTowersButton.setStyle("");
                        this.selectedUpgradeCardButton = button;
                        if(finalK < environmentManager.getListUpgradeCardsInInventory().size())
                            selectedUpgradeCard = environmentManager.getListUpgradeCardsInInventory().get(finalK);
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

//    private void updateScreen(){
//        this.playerName.setText(this.environmentManager.getPlayerName());
//        this.towerTimeList = List.of(this.tower1Time, this.tower2Time, this.tower3Time, this.tower4Time, this.tower5Time);
//        this.towerResourceList = List.of(this.tower1Resource, this.tower2Resource, this.tower3Resource, this.tower4Resource, this.tower5Resource);
//        this.towerLevelList = List.of(this.tower1Level, this.tower2Level, this.tower3Level, this.tower4Level, this.tower5Level);
//        List<Button> towerButtons = List.of(this.tower1, this.tower2, this.tower3, this.tower4, this.tower5);
//        List<Button> reservedTowerButton = List.of(this.reservedTower1, this.reservedTower2, this.reservedTower3, this.reservedTower4, this.reservedTower5);
//        List<Button> upgradeCardButton = List.of(this.upgradeCard1, this.upgradeCard2, this.upgradeCard3, this.upgradeCard4, this.upgradeCard5);
//
//        for(int i = 0; i < this.environmentManager.getCurrentTowerList().size(); ++i) {
//            int finalI = i;
//            this.updateStats((Tower)this.environmentManager.getCurrentTowerList().get(finalI), (Label)this.towerTimeList.get(finalI), (Label)this.towerResourceList.get(finalI), (Label)this.towerLevelList.get(finalI));
//            ((Button)towerButtons.get(finalI)).setText(String.valueOf(((Tower)this.environmentManager.getCurrentTowerList().get(finalI)).getName()));
//            ((Button)towerButtons.get(i)).setOnAction((event) -> {
//                this.selectedCurrentTowerIndex = finalI;
//                towerButtons.forEach((button) -> {
//                    if (button == towerButtons.get(finalI)) {
//                        this.selectedCurrentTowers = (Tower) this.environmentManager.getCurrentTowerList().get(finalI);
//                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
//                    } else {
//                        button.setStyle("");
//                    }
//                });
//            });
//        }
//
//        for(int j = 0; j < reservedTowerButton.size(); ++j){
//            int finalJ = j;
//            if(finalJ < environmentManager.getReservedTowerList().size())
//                reservedTowerButton.get(finalJ).setText(environmentManager.getReservedTowerList().get(finalJ).getName());
//            reservedTowerButton.get(finalJ).setOnAction((event) -> {
//                this.selectedReversedTowerIndex = finalJ;
//                reservedTowerButton.forEach((button) -> {
//                    if (button == reservedTowerButton.get(finalJ)) {
//                        selectedReservedTowers = environmentManager.getReservedTowerList().get(finalJ);
//                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
//                    } else {
//                        button.setStyle("");
//                    }
//                });
//            });
//        }
//
//        for(int k = 0; k < upgradeCardButton.size(); ++k){
//            int finalK = k;
//            if(finalK < environmentManager.getListUpgradeCardsInInventory().size())
//                upgradeCardButton.get(finalK).setText(environmentManager.getListUpgradeCardsInInventory().get(finalK).getName());
//            upgradeCardButton.get(finalK).setOnAction((event) -> {
//                this.selectedUpgradeCardIndex = finalK;
//                upgradeCardButton.forEach((button) -> {
//                    if (button == upgradeCardButton.get(finalK)) {
//                        selectedUpgradeCard = environmentManager.getListUpgradeCardsInInventory().get(finalK);
//                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
//                    } else {
//                        button.setStyle("");
//                    }
//                });
//            });
//        }
//        if(selectedCurrentTowers != null)
//            System.out.println("Choose current tower type: " + selectedCurrentTowers.getName());
//        if(selectedReservedTowers != null)
//            System.out.println("Choose reserved tower type: " + selectedReservedTowers.getName());
//    }
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
            environmentManager.getCurrentTowerList().remove(selectedCurrentTowers);
            environmentManager.getCurrentTowerList().add(selectedCurrentTowerIndex, selectedReservedTowers);
            environmentManager.getReservedTowerList().remove(selectedReservedTowers);
            environmentManager.getReservedTowerList().add(selectedReversedTowerIndex, selectedCurrentTowers);
        }else if(selectedCurrentTowers == null && selectedReservedTowers != null){
            environmentManager.getCurrentTowerList().add(selectedCurrentTowerIndex, selectedReservedTowers);
        }else if(selectedCurrentTowers != null && selectedReservedTowers == null){
            environmentManager.getReservedTowerList().add(selectedReversedTowerIndex, selectedCurrentTowers);
        }else
            System.out.println("Please choose 2 buttons to do the action");
        selectedCurrentTowers = null;
        selectedCurrentTowerIndex = -1;
        selectedReversedTowerIndex = -1;
        selectedReservedTowers = null;
    }

    @FXML
    private void onUpgradedClicked() {
        System.out.println("onUpgradedClicked");
        if(selectedUpgradeCard != null && selectedCurrentTowers != null){
            if(selectedUpgradeCard.getName().contains("Changing Type")){
                selectedCurrentTowers.changeTypeResource(selectedUpgradeCard.getNewTypeTower());
                System.out.println("Card type:" + selectedUpgradeCard.getNewTypeTower());
                System.out.println("New type: " + selectedCurrentTowers.getName());
            }else {
                selectedCurrentTowers.levelIncrement();
                selectedCurrentTowers.upgradeTime(selectedUpgradeCard.getImprovedTime());
                selectedCurrentTowers.upgradeResourceAmount(selectedUpgradeCard.getImprovedAmountResource());
//                System.out.println("new stat: " + selectedCurrentTowers.getLevel() + " " + selectedCurrentTowers.getRecoveryTime() +"ms ");
            }
            System.out.println("Update: " + selectedCurrentTowers.getName());
            this.updateStats((Tower) selectedCurrentTowers, (Label) this.towerTimeList.get(selectedCurrentTowerIndex), (Label) this.towerResourceList.get(selectedCurrentTowerIndex), (Label) this.towerLevelList.get(selectedCurrentTowerIndex));
            selectedCurrentTowersButton.setText(String.valueOf(selectedCurrentTowers.getName()));
//            selectedUpgradeCardButton.setText("Empty");
            selectedUpgradeCardButton.setStyle("");
            selectedCurrentTowersButton.setStyle("");
            environmentManager.getListUpgradeCardsInInventory().remove(selectedUpgradeCard);
            this.showObjectInButton(this.upgradeCardButton,null, environmentManager.getListUpgradeCardsInInventory());
        }else if (selectedUpgradeCard != null && selectedCurrentTowers == null) {
            System.out.println("Please choose the current tower to upgrade");  //Care when decrement time or not???
        }else if (selectedUpgradeCard == null && selectedCurrentTowers != null) {
            System.out.println("Please choose the card to upgrade for tower");
        }else
            System.out.println("Please choose 1 card and 1 tower to do the action");
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