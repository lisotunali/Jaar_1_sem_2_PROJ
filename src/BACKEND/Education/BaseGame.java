package BACKEND.Education;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseGame implements IGame {
    private final ArrayList currentGameQuestions = new ArrayList<>();
    private final Timer timer = new Timer();
    private final ObservableWithTypes events = new ObservableWithTypes("timer", "endGame", "newHighScore");
    private Integer currentscore = 0;
    private Integer secondsLeft = 60;
    private String correctAnswer;

    public BaseGame() {
        loadQuestions();
    }

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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    //    --------- END GETTERS/SETTERS ---------

    public void checkAnswer(String input) {
        if (correctAnswer.equalsIgnoreCase(input)) {
            currentscore++;
            System.out.println("+1 score, total = " + currentscore);
        } else {
            setSecondsLeft(secondsLeft - 10);
            events.notify("timer", BaseGame.this);
        }
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setSecondsLeft(secondsLeft - 1);
                System.out.println(secondsLeft);
                events.notify("timer", BaseGame.this);
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
        Highscores hsCurrentPerson = getCurrentPersonHs();

        if (getCurrentscore() > hsCurrentPerson.getHighScore()) {
            hsCurrentPerson.setHighscore(getCurrentscore());
            events.notify("newHighScore", this);
        }
    }

    public abstract Highscores getCurrentPersonHs();

    public void nextQuestion() {
        Collections.shuffle(currentGameQuestions);
        getNextQuestion();
        setCorrectAnswer(getCurrentCorrectAnswer());
    }

    public void loadQuestions() {
        saveQuestionsLocally(getGameQuestions());
    }

    public abstract ArrayList<?> getGameQuestions();

    public abstract String getCurrentCorrectAnswer();

    public abstract void getNextQuestion();
}
