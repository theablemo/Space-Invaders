package Controllers;

import Models.Difficulty;
import Models.EndGame;
import Models.Score;
import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class MainController {
    private static MainController instance = null;
    private User user = null;
    private Score gameScore;
    private Difficulty difficulty = null;
    private EndGame endGame = null;
    private double musicVolume = 0.3;
    private double shootingVolume = 1;
    private double destroyVolume = 1;
    private MediaPlayer musicMediaPlayer;


    public MediaPlayer getMusicMediaPlayer() {
        return musicMediaPlayer;
    }

    public void setMusicMediaPlayer(MediaPlayer musicMediaPlayer) {
        this.musicMediaPlayer = musicMediaPlayer;
    }

    public double getDestroyVolume() {
        return destroyVolume;
    }

    public double getShootingVolume() {
        return shootingVolume;
    }

    public void setDestroyVolume(double destroyVolume) {
        this.destroyVolume = destroyVolume;
    }

    public void setShootingVolume(double shootingVolume) {
        this.shootingVolume = shootingVolume;
    }

    public void setMusicVolume(double musicVolume) {
        this.musicVolume = musicVolume;
    }

    public double getMusicVolume() {
        return musicVolume;
    }

    public void setEndGame(EndGame endGame) {
        this.endGame = endGame;
    }

    public EndGame getEndGame() {
        return endGame;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    private Scene startScene,loginScene,signupScene,mainMenuScene,levelScene,changePassScene,changeUsernameScene;

    public Scene getChangePassScene() throws IOException {
        if(changePassScene == null){
            Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("/Views/ChangePassword.fxml"));
            changePassScene = new Scene(startSceneTemplate, 800, 800);
        }
        return changePassScene;
    }

    public Scene getChangeUsernameScene() throws IOException {
        if(changeUsernameScene == null){
            Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("/Views/ChangeUsername.fxml"));
            changeUsernameScene = new Scene(startSceneTemplate, 800, 800);
        }
        return changeUsernameScene;
    }

    public Scene getLevelScene() throws IOException {
        if(levelScene == null){
            Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("/Views/level.fxml"));
            levelScene = new Scene(startSceneTemplate, 800, 800);
        }
        return levelScene;
    }

    public Scene getStartScene() throws IOException {
        if(startScene == null)
        {
            Parent startSceneTemplate = FXMLLoader.load(getClass().getResource("/Views/StartPage.fxml"));
            startScene = new Scene(startSceneTemplate, 800, 800);
        }
        return startScene;
    }

    public Scene getSignupScene() throws IOException {
        if(signupScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/Signup.fxml"));
            signupScene = new Scene(pane, 800, 800);
        }
        return signupScene;
    }

    public Scene getLoginScene() throws IOException {
        if(loginScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            loginScene = new Scene(pane, 800, 800);
        }
        return loginScene;
    }

    public Scene getMainMenuScene() throws IOException {
        if(mainMenuScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
            mainMenuScene = new Scene(pane, 800, 800);
        }
        return mainMenuScene;
    }

    public void setMainMenuScene(Scene mainMenuScene) {
        this.mainMenuScene = mainMenuScene;
    }

    public void setGameScore(Score gameScore) {
        this.gameScore = gameScore;
    }

    public Score getGameScore() {
        return gameScore;
    }

    public static MainController getInstance() {
        if(instance == null)
            instance = new MainController();
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    public void makeUser(String username,String password)
    {
        setUser(new User(username,password));
    }
    public void login(String username,String password) throws Exception
    {
        User sample = User.getAllUsers().get(username);
        if(!sample.getPassword().equals(password))
        {
            throw new Exception("Password is incorrect.");
        }
        setUser(sample);
    }
    public void signout()
    {
        setUser(null);
    }
}
