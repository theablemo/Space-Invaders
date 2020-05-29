package Controllers;

import Models.Score;
import Models.User;
import javafx.scene.Scene;

public class MainController {
    private static MainController instance = null;
    private User user = null;
    private boolean inGame = false;
    private Scene currentScene = null;
    private Score gameScore;

    public void setGameScore(Score gameScore) {
        this.gameScore = gameScore;
    }

    public Score getGameScore() {
        return gameScore;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
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
