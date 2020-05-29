package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    public Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Wellcome " + MainController.getInstance().getUser().getUsername());
    }

    private static Scene gameScene = null;
    public void startNewGame(MouseEvent mouseEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Game.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        gameScene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getGameScene() {
        return gameScene;
    }

    public void showScoreBoard(MouseEvent mouseEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/ScoreBoard.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void signout(MouseEvent mouseEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Startpage.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}
