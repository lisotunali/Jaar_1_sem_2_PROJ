package Education;

import GUI.fakeDatabase;
import GUI.singletonPerson;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Game {

    private String currentGameType;
    private ArrayList currentGameQuestions = new ArrayList<>();
    private Timer timer = new Timer();
    private Integer currentscore = 0;
    private Integer currentQuestion = -1;
    private Integer secondsLeft = 60;

    private ObservableWithTypes events = new ObservableWithTypes("timer", "endGame", "newHighScore");

    public void saveQuestionsLocally(ArrayList source) {
        currentGameQuestions.clear();
        for (int i = 0; i < source.size(); i++) {
            currentGameQuestions.add(i, source.get(i));
        }
    }

    public ArrayList getCurrentGameQuestions() {
        return currentGameQuestions;
    }

    public Integer getCurrentscore() {
        return currentscore;
    }

    public void setCurrentscore(Integer currentscore) {
        this.currentscore = currentscore;
    }

    public Integer getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Integer currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String GetCurrentGameType() {
        return currentGameType;
    }

    public void setCurrentGameType(String currentGameType) {
        this.currentGameType = currentGameType;
    }

    public Integer getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(Integer secondsLeft) {
        this.secondsLeft = secondsLeft;
        if (secondsLeft <= 0) {
            endGame();
        }
    }

    public ObservableWithTypes getEvents() {
        return events;
    }

    //    --------- END GETTERS/SETTERS ---------

    public void checkAnswer(String input, String expected) {
        if (expected.equalsIgnoreCase(input)) {
            currentscore++;
            System.out.println("+1 score, total = " + currentscore);
        } else {
            setSecondsLeft(secondsLeft - 10);
            events.notify("timer", Game.this);
        }
    }

    public void startTimer(Label timerLabel) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setSecondsLeft(secondsLeft - 1);
                System.out.println(secondsLeft);
                events.notify("timer", Game.this);
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void endGame() {
        stopTimer();
        saveNewHS();
        System.out.println("game has ended");
        events.notify("endGame", this);
    }

    public void saveNewHS() {
        if (getCurrentscore() > singletonPerson.getInstance().getHS(GetCurrentGameType()).getHighScore()) {
            singletonPerson.getInstance().getHS(GetCurrentGameType()).setHighscore(getCurrentscore());
            fakeDatabase.addReadingHS(new Highscores(GetCurrentGameType(), getCurrentscore(), singletonPerson.getInstance().getName()), GetCurrentGameType());
            events.notify("newHighScore", this);
        }
    }
}
