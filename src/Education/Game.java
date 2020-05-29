package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    protected ArrayList<ImageWithName> currentGameQuestions = new ArrayList<>();
    private Integer currentscore = 0;
    private Integer currentQuestion = -1;

    public void saveQuestionsLocally() {
        currentGameQuestions.clear();
        for (int i = 0; i < fakeDatabase.getImagesDatabase().size(); i++) {
            currentGameQuestions.add(i, fakeDatabase.getImagesDatabase().get(i));
        }
    }

    public ArrayList<ImageWithName> getCurrentGameQuestions() {
        return currentGameQuestions;
    }

    public Integer getCurrentscore(){
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
}
