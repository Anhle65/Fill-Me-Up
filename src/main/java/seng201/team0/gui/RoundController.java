package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

public class RoundController {
    @FXML
    private TextField cartInfor;

    @FXML
    private Button towerButton;
    private Tower tower;
    private Cart cart;

    public void init(Stage stage){
        tower = new Tower("water",1,40,20,3,true,true);
        cart = new Cart("water", 5f,100);
        cart.setAmount(tower.getCapacity());
    }
    public void onClickedTower() {
        System.out.println("Button has been clicked");
        cart.incrementResource();

        int count = cart.getCurrentSize();
        cartInfor.setText(Integer.toString(count));
    }
}
