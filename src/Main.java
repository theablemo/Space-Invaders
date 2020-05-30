import Controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

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
