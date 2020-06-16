package BACKEND.Education;

import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;

import java.util.ArrayList;
import java.util.Collections;

public class Writing extends BaseGame {
    public Writing() {
        saveQuestionsLocally(fakeDatabase.getImagesDatabase());
        shuffleQuestions();
    }

    @Override
    public void nextQuestion() {
        if (getCurrentQuestion() + 1 == getQuestions().size()) {
            endGame();
            return;
        }

        setCurrentQuestion(getCurrentQuestion() + 1);
        setCorrectAnswer(getCurrentImage().getName());
    }

    public ImageWithName getCurrentImage() {
        return getQuestions().get(getCurrentQuestion());
    }

    public ArrayList<ImageWithName> getQuestions() {
        return getCurrentGameQuestions();
    }

    public void shuffleQuestions() {
        Collections.shuffle(getQuestions());
    }

    @Override
    public Highscores getCurrentPersonHs() {
        return singletonPerson.getInstance().getHS("writing");
    }
}
