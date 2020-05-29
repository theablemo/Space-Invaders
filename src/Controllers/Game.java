package Controllers;

import Models.Score;
import com.sun.tools.javac.Main;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public ImageView ship;
    public ArrayList<ImageView> bullets;
    public ArrayList<ImageView> enemies;
    public ArrayList<ImageView> enemyBullets;
    Score score;
    public Button toGetScene;
    public Pane pane;
    private double getDownTime = 0;
    private double shootTime = 0;
    private KeyEvent event = null;
    private boolean gotHit = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score = MainController.getInstance().getGameScore();
        enemyBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        ship.setFitWidth(90);
        ship.setFitHeight(90);
        //make enemies
        ColorAdjust colorAdjust = new ColorAdjust();
        for (int i = 0; i < 30 ; i++) {
            colorAdjust.setHue(10);
            colorAdjust.setContrast(-0.5);
            colorAdjust.setSaturation(-0.2);
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
        timer.start();
    }

    private void endGame(KeyEvent keyEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/GameResult.fxml"));
        Stage stage = (Stage)((Node)keyEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void update()
    {
        getDownTime += 0.016;
        shootTime += 0.016;
        if(enemies.isEmpty() || gotHit)
        {
            try
            {
                gotHit = false;
                endGame(event);
            }catch (Exception e){}
        }
        //enemy reached ship
        for (ImageView enemy : enemies) {
            if(enemy.intersects(ship.getBoundsInParent()))
            {
                gotHit = true;
                break;
            }
        }
        //get the bullet
        for (ImageView bullet : bullets) {
            bullet.setY(bullet.getY() - 5);
            for (ImageView enemy : enemies) {
                if(bullet.intersects(enemy.getBoundsInParent()))
                {
                    score.setScore(score.getScore()+1);
                    pane.getChildren().remove(bullet);
                    pane.getChildren().remove(enemy);
                    bullets.remove(bullet);
                    enemies.remove(enemy);
                }
            }
        }
        //shoot enemies
        if(Math.random() < 0.4 && shootTime > 0.5)
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
        //get enemy bullets
        for (ImageView enemyBullet : enemyBullets) {
            enemyBullet.setY(enemyBullet.getY() + 5);
            if(enemyBullet.intersects(ship.getBoundsInParent()))
            {
                pane.getChildren().remove(enemyBullet);
                pane.getChildren().remove(ship);
                enemyBullets.remove(enemyBullet);
                gotHit = true;
            }
        }
        //get down one row enemies
        if(getDownTime >= 5)
        {
            for (ImageView enemy : enemies) {
                enemy.setY(enemy.getY() + 90);
            }
            for (int i = 0; i < 10 ; i++) {
                Image alien = new Image("/Images/alien.png");
                ImageView iv = new ImageView();
                iv.setImage(alien);
                iv.setFitWidth(50);
                iv.setFitHeight(50);
                iv.setX(50 + (i%10)*70);
                iv.setY(100 + (i/10)*90);
                pane.getChildren().add(iv);
                enemies.add(iv);
            }
            getDownTime = 0;
        }
    }

    public void moveShip(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.RIGHT)
        {
            ship.setX(ship.getX() + 10);
            ship.setSmooth(true);
        }
        if(keyEvent.getCode() == KeyCode.LEFT)
        {
            ship.setX(ship.getX() - 10);
            ship.setSmooth(true);
        }
        if(keyEvent.getCode() == KeyCode.SPACE)
        {
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
    public void exitGame(MouseEvent mouseEvent) throws IOException {
        Score.getAllScores().remove(MainController.getInstance().getGameScore());
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MainMenu.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void newGame(MouseEvent mouseEvent) throws IOException {
        Score.getAllScores().remove(MainController.getInstance().getGameScore());
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Game.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}
