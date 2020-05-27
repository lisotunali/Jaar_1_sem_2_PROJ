package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private ImageWithName temp1;
    private ImageWithName temp2;
    private ImageWithName temp3;
    private ImageWithName temp4;
    private ArrayList<ImageWithName> temps = new ArrayList<>();

    private int randomIndex = 0;
    private int randomTempsIndex = 0;
    private static Random ran = new Random();

    private Integer currentscore = 0;
    private MinuteTimer minuteTimer = new MinuteTimer();
    private ArrayList<ImageWithName> currentGameQuestions = new ArrayList<>();

    public ImageWithName getTemp1() {
        return temp1;
    }

    public ImageWithName getTemp2() {
        return temp2;
    }

    public ImageWithName getTemp3() {
        return temp3;
    }

    public ImageWithName getTemp4() {
        return temp4;
    }

    public ImageWithName getRandomTemp(){
        randomTempsIndex = ran.nextInt(4);
        return temps.get(randomTempsIndex);
    }

    public void initialize() {
        saveQuestionsLocally();
        put4RandomQuestionsInTheTemps();
        initiateTempsArray();
    }

    public void saveQuestionsLocally() {
        currentGameQuestions.clear();
        for (int i = 0; i < fakeDatabase.getImagesDatabase().size(); i++) {
            currentGameQuestions.add(i, fakeDatabase.getImagesDatabase().get(i));
        }
    }

    public void put4RandomQuestionsInTheTemps(){
        int ran1 = ran.nextInt(currentGameQuestions.size());
        temp1 = currentGameQuestions.get(ran1);
        currentGameQuestions.remove(ran1);
        int ran2 = ran.nextInt(currentGameQuestions.size());
        temp2 = currentGameQuestions.get(ran2);
        currentGameQuestions.remove(ran2);
        int ran3 = ran.nextInt(currentGameQuestions.size());
        temp3 = currentGameQuestions.get(ran3);
        currentGameQuestions.remove(ran3);
        int ran4 = ran.nextInt(currentGameQuestions.size());
        temp4 = currentGameQuestions.get(ran4);
        currentGameQuestions.remove(ran4);
    }

    public void initiateTempsArray(){
        temps.clear();
        temps.add(temp1);
        temps.add(temp2);
        temps.add(temp3);
        temps.add(temp4);
    }

    public void checkAnswer(Integer buttonClicked) {
        if (buttonClicked == randomTempsIndex) {
            currentscore++;
            System.out.println(currentscore);
        }
    }
}
