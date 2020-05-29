package Controllers;

import Models.Score;
import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {
    private static MainController instance = null;
    private User user = null;
    private boolean inGame = false;
    private Score gameScore;

    private Scene startScene,loginScene,signupScene,mainMenuScene,gameScene,resultScene,scoreBoardScene;

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

    public Scene getGameScene() throws IOException {
        if(gameScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
            gameScene = new Scene(pane, 800, 800);
        }
        return gameScene;
    }

    public Scene getResultScene() throws IOException {
        if(resultScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/GameResult.fxml"));
            resultScene = new Scene(pane, 800, 800);
        }
        return resultScene;
    }

    public Scene getScoreBoardScene() throws IOException {
        if(scoreBoardScene == null)
        {
            Parent pane = FXMLLoader.load(getClass().getResource("/Views/GameResult.fxml"));
            scoreBoardScene = new Scene(pane, 800, 800);
        }
        return scoreBoardScene;
    }

    public void setGameScore(Score gameScore) {
        this.gameScore = gameScore;
    }

    public Score getGameScore() {
        return gameScore;
    }


    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
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
