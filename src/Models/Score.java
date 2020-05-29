package Models;

import java.util.HashMap;

public class Score {
    private User user;
    private int score;
    private static HashMap<User,Score> allScores = new HashMap<>();
    public Score(User user , int score)
    {
        this.user = user;
        this.score = score;
        allScores.put(user,this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static HashMap<User, Score> getAllScores() {
        return allScores;
    }
}

