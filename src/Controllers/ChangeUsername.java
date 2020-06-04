package Controllers;

import Models.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeUsername {
    public Label errorLabel;
    public TextField confirmNewUsername;
    public TextField newUsername;

    public void submit(MouseEvent mouseEvent) {
        errorLabel.setTextFill(Paint.valueOf("F51313"));
        if(newUsername.getText().equals(""))
        {
            errorLabel.setText("Enter a username");
        }
        else if(!newUsername.getText().equals(confirmNewUsername.getText()))
        {
            errorLabel.setText("Your usernames doesn't match");
        }
        else if(newUsername.getText().equals(MainController.getInstance().getUser().getUsername()))
        {
            errorLabel.setText("Your new username is same as your old one");
        }
        else
        {
            for (Score score : Score.getAllScores()) {
                if(score.getUsername().equals(MainController.getInstance().getUser().getUsername()))
                {
                    score.setUsername(newUsername.getText());
                }
            }
            MainController.getInstance().getUser().setUsername(newUsername.getText());
            errorLabel.setText("Your username has been successfully changed");
            errorLabel.setTextFill(Paint.valueOf("13F513"));
        }
        newUsername.clear();
        confirmNewUsername.clear();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        newUsername.clear();
        confirmNewUsername.clear();
        errorLabel.setText("");
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Settings.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}
