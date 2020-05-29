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
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MainMenu.fxml"));
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane, 800, 800);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }
}
