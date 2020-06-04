import Controllers.MainController;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        music();
        primaryStage.setTitle("MHD Space Invaders");
        primaryStage.setScene(MainController.getInstance().getStartScene());
        primaryStage.show();
    }

    MediaPlayer mediaPlayer;
    public void music() {
        String s = "src/Musics/back.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.setVolume(MainController.getInstance().getMusicVolume());
        MainController.getInstance().setMusicMediaPlayer(mediaPlayer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
