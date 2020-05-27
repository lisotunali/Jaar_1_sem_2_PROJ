package Education;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    Integer currentscore = 0;
    MinuteTimer minuteTimer = new MinuteTimer();
    ArrayList<ImageWithName> allQuestions = new ArrayList<>();
    ArrayList<ImageWithName> currentGameQuestions = new ArrayList<>();
    static Random ran = new Random();
    int randomIndex = 0;

    public void askRandomQuestion(){
        randomIndex = ran.nextInt(currentGameQuestions.size());
        //TextField.setText(currentGameQuestions.get(randomIndex).getName());
        currentGameQuestions.remove(randomIndex);
    }

    public void initialize(){
       generateGameQuestions();
    }

    public void generateGameQuestions(){
       for (int i = 0; i <allQuestions.size(); i++){
           currentGameQuestions.set(i, allQuestions.get(i));
       }
    }

    public void checkAnswer(String answer){
        if (answer == currentGameQuestions.get(randomIndex).getName()){
            currentscore++;
        }
    }
}
