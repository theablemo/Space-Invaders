package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    public Slider musicSlider;
    public Slider shootingSlider;
    public Label passwordField;
    public Label usernameField;
    public Slider destroySlider;
    public Button showPasswordButton;
    boolean showHidePass = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicSlider.setValue(MainController.getInstance().getMusicVolume() * 100);
        shootingSlider.setValue(MainController.getInstance().getShootingVolume() * 100);
        destroySlider.setValue(MainController.getInstance().getDestroyVolume() * 100);
        passwordField.setText("*******");
        usernameField.setText(MainController.getInstance().getUser().getUsername());
    }

    public void showPassword(MouseEvent mouseEvent) {
        if(showHidePass)
        {
            passwordField.setText(MainController.getInstance().getUser().getPassword());
            showPasswordButton.setText("Hide Password");
        }
        else
        {
            passwordField.setText("*******");
            showPasswordButton.setText("Show Password");
        }
        showHidePass = !showHidePass;
    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getChangePassScene());
        stage.show();
    }

    public void changeUsername(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getChangeUsernameScene());
        stage.show();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent pane = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
        Scene mainMenuScene = new Scene(pane, 800, 800);
        stage.setScene(mainMenuScene);
        MainController.getInstance().setMainMenuScene(mainMenuScene);
        stage.show();
    }

    public void changeMusicVolume(MouseEvent mouseEvent) {
        MainController.getInstance().setMusicVolume(musicSlider.getValue() / 100);
        MainController.getInstance().getMusicMediaPlayer().setVolume(musicSlider.getValue() / 100);
    }

    public void changeShootVolume(MouseEvent mouseEvent) {
        MainController.getInstance().setShootingVolume(shootingSlider.getValue() / 100);
    }

    public void changeDestroyVolume(MouseEvent mouseEvent) {
        MainController.getInstance().setDestroyVolume(destroySlider.getValue() / 100);
    }

    public void muteMusic(MouseEvent mouseEvent) {
        musicSlider.setValue(0);
        MainController.getInstance().setMusicVolume(0);
        MainController.getInstance().getMusicMediaPlayer().setVolume(0);
    }

    public void muteShooting(MouseEvent mouseEvent) {
        shootingSlider.setValue(0);
        MainController.getInstance().setShootingVolume(0);
    }

    public void muteDestroy(MouseEvent mouseEvent) {
        destroySlider.setValue(0);
        MainController.getInstance().setDestroyVolume(0);
    }
}
