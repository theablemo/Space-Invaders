package Controllers;

import Models.User;

public class MainController {
    private static MainController instance = null;
    private User user = null;

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
