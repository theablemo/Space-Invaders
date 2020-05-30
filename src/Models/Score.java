package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Score {
    private User user;
    private int score;
    private String username;
    private static ArrayList<Score> allScores = new ArrayList<>();
    public Score(User user , int score)
    {
        this.user = user;
        this.score = score;
        this.username = user.getUsername();
        allScores.add(this);
    }

    public static ArrayList<Score> sortScore()
    {
        ArrayList<Integer> listScores = new ArrayList<>();
        ArrayList<Score> backup = new ArrayList<>();
        ArrayList<Score> result = new ArrayList<>();
        backup.addAll(allScores);
        for (Score score : allScores) {
            listScores.add(score.getScore());
        }
        Collections.sort(listScores);
        for (int i = listScores.size()-1; i >= 0; i--) {
            for (Score score : backup) {
                if(listScores.get(i) == score.getScore())
                {
                    result.add(score);
                    backup.remove(score);
                }
            }
        }
        return result;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static ArrayList<Score> getAllScores() {
        return allScores;
    }
}

