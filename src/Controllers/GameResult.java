package Controllers;

import Models.EndGame;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameResult implements Initializable {

    public Label score;
    public Label result;

    public void getToMain(MouseEvent mouseEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MainMenu.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String scoreText = "Score: 0";
        if(MainController.getInstance().getGameScore() != null)
             scoreText = "Score: " + MainController.getInstance().getGameScore().getScore();
        score.setText(scoreText);
        MainController.getInstance().setGameScore(null);
        if(MainController.getInstance().getEndGame() == EndGame.LOSE)
        {
            result.setText("YOU LOST!");
            result.setTextFill(Paint.valueOf("#FB2716"));
        }
        else if(MainController.getInstance().getEndGame() == EndGame.WIN)
        {
            result.setText("YOU WON!");
            result.setTextFill(Paint.valueOf("#24FB16"));
        }
    }
}
