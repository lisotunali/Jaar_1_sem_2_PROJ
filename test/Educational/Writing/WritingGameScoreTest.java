package Educational.Writing;

import BACKEND.Education.BaseGame;
import BACKEND.Education.ImageWithName;
import BACKEND.Education.Math;
import BACKEND.Education.Writing;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WritingGameScoreTest {

    void addQuestsToDatabase() {
        fakeDatabase.getImagesDatabase().add(new ImageWithName("bee", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/bee.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("doctor", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/doctor.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("dog", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/dog.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("elephant", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/elephant.png"))));
    }

    @Test
    public void ChangeScoreTest1() {
        addQuestsToDatabase();
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame writingGame = new Writing();
        for (int i = 0; i < 10; i++) {
            writingGame.nextQuestion();
            System.out.println("Current answer: " + writingGame.getCorrectAnswer());
            writingGame.checkAnswer(writingGame.getCorrectAnswer());
            System.out.println("Current score: " + writingGame.getCurrentscore());
        }
        writingGame.saveNewHS();
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("writing").getHighScore());
        assertEquals(10, singletonPerson.getInstance().getHS("writing").getHighScore());
    }

    @Test
    public void ChangeScoreTest2() {
        addQuestsToDatabase();
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame writingGame1 = new Math();
        for (int i = 0; i < 10; i++) {
            writingGame1.nextQuestion();
            System.out.println("Current answer: " + writingGame1.getCorrectAnswer());
            writingGame1.checkAnswer(writingGame1.getCorrectAnswer());
            System.out.println("Current score: " + writingGame1.getCurrentscore());
        }
        writingGame1.saveNewHS();
        writingGame1.endGame();
        //After we've saved a new Highscore, let's make sure it doesn't get overwritten by a lower score. The score should still be 10.
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("math").getHighScore());
        BaseGame writingGame2 = new Math();
        for (int i = 0; i < 5; i++) {
            writingGame2.nextQuestion();
            System.out.println("Current answer: " + writingGame2.getCorrectAnswer());
            writingGame2.checkAnswer(writingGame2.getCorrectAnswer());
            System.out.println("Current score: " + writingGame2.getCurrentscore());
        }
        writingGame2.saveNewHS();
        writingGame2.endGame();
        System.out.println("Highscore: " + singletonPerson.getInstance().getHS("math").getHighScore());
        assertEquals(10, singletonPerson.getInstance().getHS("math").getHighScore());
    }
}
