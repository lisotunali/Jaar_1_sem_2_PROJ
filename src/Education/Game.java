package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {

    protected ArrayList<ImageWithName> currentGameQuestions = new ArrayList<>();
    private Random ran = new Random();
    protected int randomIndex = 0;

    private Integer currentscore = 0;


    public abstract void checkAnswer(String input);

    public void initialize() {
    }

    public void saveQuestionsLocally() {
        currentGameQuestions.clear();
        for (int i = 0; i < fakeDatabase.getImagesDatabase().size(); i++) {
            currentGameQuestions.add(i, fakeDatabase.getImagesDatabase().get(i));
        }
    }

    public ArrayList<ImageWithName> getCurrentGameQuestions() {
        return currentGameQuestions;
    }

    public int getRandomIndex() {
        return randomIndex;
    }

    public void setRandomIndex(int randomIndex) {
        this.randomIndex = randomIndex;
    }

    public Random getRan() {
        return ran;
    }

    public Integer getCurrentscore(){
        return currentscore;
    }

    public void setCurrentscore(Integer currentscore) {
        this.currentscore = currentscore;
    }

}
