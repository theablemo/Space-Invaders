package Controllers;

import Models.User;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public Label errorLabel;
    public TextField password;
    public TextField username;

    public void loginAction(MouseEvent mouseEvent) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if(usernameText.equals(""))
        {
            errorLabel.setText("Enter a username");
            username.clear();
            password.clear();
            throw new Exception();
        }
        if(passwordText.equals(""))
        {
            errorLabel.setText("Enter a password");
            username.clear();
            password.clear();
            throw new Exception();
        }
        if(!User.userExists(usernameText))
        {
            errorLabel.setText("This username does not exist!");
            username.clear();
            password.clear();
            throw new Exception();
        }
        try {
            MainController.getInstance().login(usernameText,passwordText);
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(MainController.getInstance().getMainMenuScene());
            stage.show();
        }catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getStartScene());
        stage.show();
    }
}
