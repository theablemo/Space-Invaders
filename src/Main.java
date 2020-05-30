import Controllers.MainController;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MHD Space Invaders");
        primaryStage.setScene(MainController.getInstance().getStartScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
