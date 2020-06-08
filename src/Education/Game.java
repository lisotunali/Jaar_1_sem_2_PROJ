package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    protected ArrayList currentGameQuestions = new ArrayList<>();
    private Integer currentscore = 0;
    private Integer currentQuestion = -1;
    private String  currentGameType;

    public void saveQuestionsLocally(ArrayList source) {
        currentGameQuestions.clear();
        for (int i = 0; i < source.size(); i++) {
            currentGameQuestions.add(i, source.get(i));
        }
    }

    public ArrayList getCurrentGameQuestions() {
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

    public String GetCurrentGameType(){ return currentGameType;}

    public void setCurrentGameType(String currentGameType ){ this.currentGameType = currentGameType; }
}
