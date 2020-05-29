package Education;

import GUI.*;
import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Reading extends Education.Game {

    @Override
    public void checkAnswer(String buttonClicked) {
        if (buttonClicked.equals(Integer.toString(randomIndex))) {
            setCurrentscore(getCurrentscore()+1);
        }
        else {
            setCurrentscore(getCurrentscore()-1);
        }
    }



}
