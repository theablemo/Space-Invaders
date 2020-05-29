import Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MHD Space Invaders");
        Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("Views/StartPage.fxml"));
        Scene startPageScene = new Scene(startSceneTemplate, 800, 800);

//        MainController.getInstance().setCurrentScene(startPageScene);
//        if(MainController.getInstance().isInGame())
//        {
//            Scene gameScene = MainController.getInstance().getCurrentScene();
//            gameScene.setOnKeyPressed(e -> {
//                if(e.getCode() == KeyCode.RIGHT)
//
//
//            });
//        }

        primaryStage.setScene(startPageScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
