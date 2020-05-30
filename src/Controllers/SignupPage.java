package Controllers;

import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupPage {


    public Label errorLabel;
    public TextField password;
    public TextField username;

    public void signupAction(MouseEvent mouseEvent) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if(usernameText.equals(""))
        {
            errorLabel.setText("Choose a username");
            username.clear();
            password.clear();
            throw new Exception();
        }
        if(passwordText.equals(""))
        {
            errorLabel.setText("Choose a password");
            username.clear();
            password.clear();
            throw new Exception();
        }
        if(User.userExists(usernameText))
        {
            errorLabel.setText("This username Exists!");
            username.clear();
            password.clear();
            throw new Exception();
        }
        MainController.getInstance().makeUser(usernameText,passwordText);
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent pane = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
        Scene mainMenuScene = new Scene(pane, 800, 800);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getStartScene());
        stage.show();
    }
}
