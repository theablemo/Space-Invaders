import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
//    Scene GameScene,LoginScene,MainMenuScene,ScoreBoardScene,SignupScen
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MHD Space Invaders");

        Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("Views/StartPage.fxml"));
        Scene startPage = new Scene(startSceneTemplate, 800, 800);
        primaryStage.setScene(startPage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
