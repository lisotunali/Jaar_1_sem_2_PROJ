package BACKEND.Education;

import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;

import java.util.ArrayList;
import java.util.Random;

public class Reading extends BaseGame {
    private final ArrayList<ImageWithName> currentQuestions = new ArrayList<>();

    public ArrayList<ImageWithName> getQuestion() {
        return currentQuestions;
    }

    @Override
    public void getNextQuestion() {
        currentQuestions.clear();

        ArrayList<ImageWithName> questions = super.getCurrentGameQuestions();

        for (int i = 0; i < 4; i++) {
            currentQuestions.add(questions.get(i));
        }
    }

    @Override
    public String getCurrentCorrectAnswer() {
        return currentQuestions.get(new Random().nextInt(currentQuestions.size())).getName();
    }

    @Override
    public Highscores getCurrentPersonHs() {
        return singletonPerson.getInstance().getHS("reading");
    }

    public ArrayList<ImageWithName> getGameQuestions() {
        return fakeDatabase.getImagesDatabase();
    }
}
