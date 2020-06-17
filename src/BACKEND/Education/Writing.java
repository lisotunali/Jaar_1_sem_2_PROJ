package BACKEND.Education;

import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;

import java.util.ArrayList;

public class Writing extends BaseGame {
    private ImageWithName currentQuestion;

    @Override
    public Highscores getCurrentPersonHs() {
        return singletonPerson.getInstance().getHS("writing");
    }

    public ImageWithName getQuestion() {
        return currentQuestion;
    }

    @Override
    public ArrayList<ImageWithName> getGameQuestions() {
        return fakeDatabase.getImagesDatabase();
    }

    @Override
    public String getCurrentCorrectAnswer() {
        return currentQuestion.getName();
    }

    @Override
    public void getNextQuestion() {
        currentQuestion = (ImageWithName) super.getCurrentGameQuestions().get(0);
    }
}
