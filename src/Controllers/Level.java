package Controllers;

import Models.Difficulty;
import Models.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Level {

    public void easy(MouseEvent mouseEvent) throws IOException {
        MainController.getInstance().setGameScore(new Score(MainController.getInstance().getUser(),0));
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Game.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
        MainController.getInstance().setDifficulty(Difficulty.EASY);
    }

    public void medium(MouseEvent mouseEvent) throws IOException {
        MainController.getInstance().setGameScore(new Score(MainController.getInstance().getUser(),0));
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Game.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
        MainController.getInstance().setDifficulty(Difficulty.MEDIUM);
    }

    public void hard(MouseEvent mouseEvent) throws IOException {
        MainController.getInstance().setGameScore(new Score(MainController.getInstance().getUser(),0));
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Game.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
        MainController.getInstance().setDifficulty(Difficulty.HARD);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getMainMenuScene());
        stage.show();
    }
}
