package Controllers;

import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SignupPage {


    public Label errorLabel;
    public TextField password;
    public TextField username;

    public void signupAction(MouseEvent mouseEvent) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if(User.userExists(usernameText))
        {
            errorLabel.setText("This username Exists!");
            errorLabel.setTextFill(Paint.valueOf("DD3022"));
            throw new Exception();
        }
        MainController.getInstance().makeUser(usernameText,passwordText);

        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MainMenu.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}
