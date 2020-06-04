package Controllers;

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

public class ChangePassword {
    public Label errorLabel;
    public TextField confirmNewPass;
    public TextField newPass;

    public void back(MouseEvent mouseEvent) throws IOException {
        newPass.clear();
        confirmNewPass.clear();
        errorLabel.setText("");
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Settings.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void submit(MouseEvent mouseEvent){
        errorLabel.setTextFill(Paint.valueOf("F51313"));
        if(newPass.getText().equals(""))
        {
            errorLabel.setText("Enter a password");
        }
        else if(!newPass.getText().equals(confirmNewPass.getText()))
        {
            errorLabel.setText("Your passwords doesn't match");
        }
        else if(newPass.getText().equals(MainController.getInstance().getUser().getPassword()))
        {
            errorLabel.setText("Your new password is same as your old one");
        }
        else
        {
            MainController.getInstance().getUser().setPassword(newPass.getText());
            errorLabel.setText("Your password has been successfully changed");
            errorLabel.setTextFill(Paint.valueOf("13F513"));
        }
        newPass.clear();
        confirmNewPass.clear();
    }
}
