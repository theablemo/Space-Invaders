package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    public Label welcomeText;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Wellcome " + MainController.getInstance().getUser().getUsername());
    }
}
