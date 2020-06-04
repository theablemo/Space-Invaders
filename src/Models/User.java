package Models;

import java.util.HashMap;

public class User {
    private static HashMap<String, User> allUsers = new HashMap<>();
    private String username;
    private String password;
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        allUsers.put(username,this);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        allUsers.remove(this.username);
        allUsers.put(username,this);
        this.username = username;
    }

    public static HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    public static boolean userExists(String username)
    {
        for (String s : allUsers.keySet()) {
            if(s.equals(username))
                return true;
        }
        return false;
    }
}
