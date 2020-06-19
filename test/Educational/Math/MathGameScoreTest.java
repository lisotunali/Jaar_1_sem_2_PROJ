package Educational.Math;

import BACKEND.Education.BaseGame;
import BACKEND.Education.Math;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import GUI.Controllers.Utility.singletonPerson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathGameScoreTest {
    //Score system
    //Change the Highscore
    @Test
    public void ChangeScoreTest1() {
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame mathGame = new Math();
        for (int i = 0; i < 10; i++) {
            mathGame.nextQuestion();
            System.out.println("Current answer: " + mathGame.getCorrectAnswer());
            mathGame.checkAnswer(mathGame.getCorrectAnswer());
            System.out.println("Current score: " + mathGame.getCurrentscore());
        }
        mathGame.saveNewHS();
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("math").getHighScore());
        assertEquals(10, singletonPerson.getInstance().getHS("math").getHighScore());
    }

    @Test
    public void ChangeScoreTest2() {
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame mathGame1 = new Math();
        for (int i = 0; i < 10; i++) {
            mathGame1.nextQuestion();
            System.out.println("Current answer: " + mathGame1.getCorrectAnswer());
            mathGame1.checkAnswer(mathGame1.getCorrectAnswer());
            System.out.println("Current score: " + mathGame1.getCurrentscore());
        }
        mathGame1.saveNewHS();
        mathGame1.endGame();
        //After we've saved a new Highscore, let's make sure it doesn't get overwritten by a lower score. The score should still be 10.
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("math").getHighScore());
        BaseGame mathGame2 = new Math();
        for (int i = 0; i < 5; i++) {
            mathGame2.nextQuestion();
            System.out.println("Current answer: " + mathGame2.getCorrectAnswer());
            mathGame2.checkAnswer(mathGame2.getCorrectAnswer());
            System.out.println("Current score: " + mathGame2.getCurrentscore());
        }
        mathGame2.saveNewHS();
        mathGame2.endGame();
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("math").getHighScore());
        assertEquals(10, singletonPerson.getInstance().getHS("math").getHighScore());
    }
}
