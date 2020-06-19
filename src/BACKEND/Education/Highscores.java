package BACKEND.Education;

public class Highscores {
    private String gameName;
    private Integer highScore;
    private String playerName;

    public Highscores(String gameName, Integer highScore, String playerName){
        this.gameName = gameName;
        this.highScore = highScore;
        this.playerName = playerName;
    }
    public String getGameName() { return gameName;
    }
    public void setGameName(String gameName) { this.gameName = gameName;
    }
    public Integer getHighScore() { return highScore;
    }
    public void setHighscore(Integer highScore) { this.highScore = highScore;
    }
    public String getPlayerName(){ return playerName;}
}
