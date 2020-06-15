package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class Reading extends Game {
    private ArrayList<ImageWithName> temps = new ArrayList<>();

    public Reading() {
        // Need to figure out a way to remove this
        // Or we can just override saveNewHS
        super.setCurrentGameType("reading");
    }

    @Override
    public void nextQuestion() {
        setCurrentQuestion(getCurrentQuestion() + 1);
        saveQuestionsLocally(fakeDatabase.getImagesDatabase());

        set4RandomTemps();
        setCorrectAnswer();
    }

    private void set4RandomTemps() {
        temps.clear();

        ArrayList<ImageWithName> questions = getCurrentGameQuestions();
        Collections.shuffle(questions);

        for (int i = 0; i < 4; i++) {
            temps.add(questions.get(i));
        }
    }

    public ArrayList<ImageWithName> getTemps() {
        return temps;
    }

    private void setCorrectAnswer() {
        super.setCorrectAnswer(temps.get(0).getName());
        Collections.shuffle(temps);
    }
}
