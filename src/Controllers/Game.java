package Controllers;

import Models.Difficulty;
import Models.EndGame;
import Models.Score;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public ImageView ship;
    public ArrayList<ImageView> bullets;
    public ArrayList<ImageView> enemies;
    public ArrayList<ImageView> enemyBullets;
    public ArrayList<ImageView> toRemove;
    public Label showScore;
    Score score;
    public Button toGetScene;
    public Pane pane;
    private double getDownTime = 0;
    private double shootTime = 0;
    private KeyEvent event;
    private boolean gotHit;
    AnimationTimer stopTimer;

    Color[] presetColors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.BLACK, Color.WHITE};
    Color targetColor;
    public static double map(double value, double start, double stop, double targetStart, double targetStop) {
        return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gotHit = false;
        event = null;
        score = MainController.getInstance().getGameScore();
        enemyBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        toRemove = new ArrayList<>();
        ship.setFitWidth(90);
        ship.setFitHeight(90);
        //make enemies
        ColorAdjust colorAdjust = new ColorAdjust();
        int selectColor = 0;
        for (int i = 0; i < 30 ; i++) {
            if((i%10)==0)
            {
                colorAdjust = new ColorAdjust();
                selectColor = (i/10)%8;
                targetColor = presetColors[selectColor];
                double hue = map( (targetColor.getHue() + 180) % 360, 0, 360, -1, 1);
                colorAdjust.setHue(hue);

                double saturation = targetColor.getSaturation();
                colorAdjust.setSaturation(saturation);

                double brightness = map( targetColor.getBrightness(), 0, 1, -1, 0);
                colorAdjust.setBrightness(brightness);
            }
            Image alien = new Image("/Images/alien.png");
            ImageView iv = new ImageView();
            iv.setImage(alien);
            iv.setFitWidth(50);
            iv.setFitHeight(50);
            iv.setX(50 + (i%10)*70);
            iv.setY(100 + (i/10)*90);
            iv.setEffect(colorAdjust);
            pane.getChildren().add(iv);
            enemies.add(iv);
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                    update();
            }
        };
        stopTimer = timer;
        timer.start();
    }

    private void endGame() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/GameResult.fxml"));
        stopTimer.stop();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void enemyShoot()
    {
        int shootinEnemy = (int)(enemies.size()*Math.random());
        ImageView enemyToShoot = enemies.get(shootinEnemy);
        Image tir = new Image("/Images/tirReversed.png");
        ImageView tirEnemy = new ImageView();
        tirEnemy.setImage(tir);
        tirEnemy.setFitWidth(15);
        tirEnemy.setFitHeight(15);
        tirEnemy.setX(enemyToShoot.getX() + 38);
        tirEnemy.setY(enemyToShoot.getY() - 30);
        pane.getChildren().add(tirEnemy);
        enemyBullets.add(tirEnemy);
        shootTime = 0;
    }
    public void shipShoot()
    {
        for (ImageView bullet : bullets) {
            bullet.setY(bullet.getY() - 10);
            for (ImageView enemy : enemies) {
                if(bullet.intersects(enemy.getBoundsInParent()))
                {
                    musicDestroy();
                    score.setScore(score.getScore()+1);
                    toRemove.add(bullet);
                    toRemove.add(enemy);
                }
            }
        }
    }

    public void enemyBulletMove()
    {
        for (ImageView enemyBullet : enemyBullets) {
            enemyBullet.setY(enemyBullet.getY() + 5);
            if(enemyBullet.intersects(ship.getBoundsInParent()))
            {
                gotHit = true;
            }
        }
    }
    public void getDownRow()
    {
        for (ImageView enemy : enemies) {
            enemy.setY(enemy.getY() + 90);
        }
        //add new row
        Random random = new Random();
        int selectColor = random.nextInt(7);
        ColorAdjust colorAdjust = new ColorAdjust();
        targetColor = presetColors[selectColor];
        double hue = map( (targetColor.getHue() + 180) % 360, 0, 360, -1, 1);
        colorAdjust.setHue(hue);
        double saturation = targetColor.getSaturation();
        colorAdjust.setSaturation(saturation);
        double brightness = map( targetColor.getBrightness(), 0, 1, -1, 0);

        for (int i = 0; i < 10 ; i++) {
            colorAdjust.setBrightness(brightness);
            Image alien = new Image("/Images/alien.png");
            ImageView iv = new ImageView();
            iv.setImage(alien);
            iv.setFitWidth(50);
            iv.setFitHeight(50);
            iv.setX(50 + (i%10)*70);
            iv.setY(100 + (i/10)*90);
            iv.setEffect(colorAdjust);
            pane.getChildren().add(iv);
            enemies.add(iv);
        }
        getDownTime = 0;
    }
    public void update() {
        showScore.setText("Score: " + score.getScore());
        getDownTime += 0.016;
        shootTime += 0.016;
        toRemove.clear();
        if(enemies.isEmpty())
        {
            try {
                MainController.getInstance().setEndGame(EndGame.WIN);
                gotHit = false;
                endGame();
            } catch (Exception e){
            }
        }
        if(gotHit)
        {
            try {
                MainController.getInstance().setEndGame(EndGame.LOSE);
                gotHit = false;
                endGame();
            } catch (Exception e){
            }
        }
        //enemy reached ship
        for (ImageView enemy : enemies) {
            if(enemy.intersects(ship.getBoundsInParent()))
            {
                gotHit = true;
                break;
            }
        }
        //shoot enemies
        if(MainController.getInstance().getDifficulty() == Difficulty.EASY)
        {
            if(Math.random() < 0.1 && shootTime > 1.5)
                enemyShoot();
        }
        else if(MainController.getInstance().getDifficulty() == Difficulty.MEDIUM)
        {
            if(Math.random() < 0.3 && shootTime > 1)
                enemyShoot();
        }
        else if(MainController.getInstance().getDifficulty() == Difficulty.HARD)
        {
            if(Math.random() < 0.7 && shootTime > 0.5)
                enemyShoot();
        }
        //get the bullet
        shipShoot();
        //get enemy bullets
        enemyBulletMove();
        //get down one row enemies
        if(MainController.getInstance().getDifficulty() == Difficulty.EASY)
        {
            if(getDownTime >= 7)
                getDownRow();
        }
        else if(MainController.getInstance().getDifficulty() == Difficulty.MEDIUM)
        {
            if(getDownTime >= 5)
                getDownRow();
        }
        else if(MainController.getInstance().getDifficulty() == Difficulty.HARD)
        {
            if(getDownTime >= 3)
                getDownRow();
        }
        //remove object from pane
        pane.getChildren().removeAll(toRemove);
        bullets.removeAll(toRemove);
        enemies.removeAll(toRemove);
    }

    public void moveShip(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.RIGHT)
        {
            ship.setX(ship.getX() + 30);
            ship.setSmooth(true);
        }
        if(keyEvent.getCode() == KeyCode.LEFT)
        {
            ship.setX(ship.getX() - 30);
            ship.setSmooth(true);
        }
        if(keyEvent.getCode() == KeyCode.SPACE)
        {
            musicShoot();
            Image fire = new Image("/Images/tir.png");
            ImageView iv = new ImageView();
            iv.setImage(fire);
            iv.setFitWidth(15);
            iv.setFitHeight(15);
            iv.setX(ship.getX() + 38);
            iv.setY(ship.getY() - 30);
            pane.getChildren().add(iv);
            bullets.add(iv);
        }
        event = keyEvent;
    }

    public void musicShoot() {
        MediaPlayer mediaPlayer;
        String s = "src/Musics/gun.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        mediaPlayer.setVolume(MainController.getInstance().getShootingVolume());
    }
    public void musicDestroy() {
        MediaPlayer mediaPlayer;
        String s = "src/Musics/hit.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        mediaPlayer.setVolume(MainController.getInstance().getDestroyVolume());
    }

    public void exitGame(MouseEvent mouseEvent) throws IOException {
        Score.getAllScores().remove(MainController.getInstance().getGameScore());
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getMainMenuScene());
        stage.show();
    }

    public void newGame(MouseEvent mouseEvent) throws IOException {
        Score.getAllScores().remove(MainController.getInstance().getGameScore());
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(MainController.getInstance().getLevelScene());
        stage.show();
    }
}
