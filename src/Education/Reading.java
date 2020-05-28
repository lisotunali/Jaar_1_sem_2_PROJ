package Education;

import GUI.fakeDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Reading extends Game {

    @Override
    public void checkAnswer(String buttonClicked) {
        if (buttonClicked.equals(Integer.toString(randomIndex))) {
            currentscore++;
            System.out.println(currentscore);
        }
    }
}
