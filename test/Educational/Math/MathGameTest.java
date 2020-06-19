package Educational.Math;

import BACKEND.Education.BaseGame;
import BACKEND.Education.Math;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class MathGameTest {

    //Point system
    //Test if a point is awarded after the user answered correctly.
    @Test
    public void AddPointTest1() {
        BaseGame mathGame = new Math();
        mathGame.nextQuestion();
        System.out.println("Current answer: " + mathGame.getCorrectAnswer());
        mathGame.checkAnswer(mathGame.getCorrectAnswer());
        System.out.println("Current score: " + mathGame.getCurrentscore());
        assertEquals(1, mathGame.getCurrentscore());
    }

    //Test if a point is awarded after the user answered incorrectly.
    @Test
    public void AddPointTest2() {
        BaseGame mathGame = new Math();
        mathGame.nextQuestion();
        System.out.println("Current answer: " + mathGame.getCorrectAnswer());
        String wrongAnswer = mathGame.getCorrectAnswer() + 1;
        mathGame.checkAnswer(wrongAnswer);
        System.out.println("Current score: " + mathGame.getCurrentscore());
        assertNotEquals(1, mathGame.getCurrentscore());
    }

    //Test if a point is awarded after the user answered incorrectly.
    @Test
    public void AddPointTest3() {
        BaseGame mathGame = new Math();
        mathGame.nextQuestion();
        System.out.println("Current answer: " + mathGame.getCorrectAnswer());
        String wrongAnswer = mathGame.getCorrectAnswer() + 1;
        mathGame.checkAnswer("verkeerd antwoord");
        System.out.println("Current score: " + mathGame.getCurrentscore());
        assertNotEquals(1, mathGame.getCurrentscore());
    }
}
