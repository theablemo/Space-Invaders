package Controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public ImageView ship;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ship.setFitWidth(90);
        ship.setFitHeight(90);
    }
    public void moveShip(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.A)
        {
            ship.setX(ship.getX() + 5);
        }
        if(keyEvent.getCode() == KeyCode.D)
        {
            ship.setX(ship.getX() - 5);
        }
    }
}
