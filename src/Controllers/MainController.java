package Controllers;

import Models.Difficulty;
import Models.EndGame;
import Models.Score;
import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {
    private static MainController instance = null;
    private User user = null;
    private Score gameScore;
    private Difficulty difficulty = null;
    private EndGame endGame = null;

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

    private Scene startScene,loginScene,signupScene,mainMenuScene,levelScene;

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
