package BACKEND.Education;

public interface IGame {
    void nextQuestion();

    void checkAnswer(String input);

    void startTimer();

    void stopTimer();

    void endGame();

    void saveNewHS();
}
