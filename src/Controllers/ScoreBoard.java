package Controllers;

import Models.Score;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreBoard implements Initializable {
    public TableView<Score> tableScore;
    public TableColumn<Score,String> userName = new TableColumn<>("Username");
    public TableColumn<Score,Integer> score = new TableColumn<>("Score");

    ObservableList<Score> getScores(){
        ObservableList<Score> result =  FXCollections.observableArrayList();
        result.addAll(Score.getAllScores());
        return result;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        userName.setMinWidth(200);
        score.setMinWidth(200);
        tableScore.setItems(getScores());
        tableScore.getColumns().add(userName);
        tableScore.getColumns().add(score);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getMainMenuScene());
        stage.show();
    }
}
